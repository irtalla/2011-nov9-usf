package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserJDBCPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Integer add(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into users values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getFirstName());
			pstmt.setString(2, t.getLastName());
			pstmt.setString(3, t.getEmail());
			pstmt.setInt(4, t.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				u = t;
				u.setId(rs.getInt(1));
				
				sql = "insert into login values (default, ?, ?, "
						+ "(select id from users where id = ?)"
						+ ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getUsername());
				pstmt.setString(2, t.getPassword());
				pstmt.setInt(3, t.getId());
				pstmt.executeUpdate();
				
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				if (e.getMessage().contains("username")) {
					throw new NonUniqueUsernameException();
				} else if (e.getMessage().contains("email")) {
					throw new NonUniqueEmailException();
				} else {
					e.printStackTrace();
				}
			} else if (e.getMessage().contains("violates check constraint")) {
				if (e.getMessage().contains("email")) {
					throw new InvalidEmailException();
				} else {
					e.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		
		return u.getId();
		
	}
	
	@Override
	public User getById(Integer id) {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where user_id = " + id;
			Set<User> users = getUserInfo(sql, conn);
			for (User user : users) {
				if (user.getId().equals(id)) {
					u = user;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User getByUsername(String username) {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where l.username = '" + username + "'";
			Set<User> users = getUserInfo(sql, conn);
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					u = user;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User getByEmail(String email) {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where u.email = '" + email + "'";
			Set<User> users = getUserInfo(sql, conn);
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					u = user;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public Set<User> getByRole(Role role) {
		Set<User> users = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where role_id = " + role.getId();
			users = getUserInfo(sql, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public Set<User> getAll() {
		Set<User> users = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			users = getUserInfo(sql, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public void update(User t) throws NonUniqueUsernameException, NonUniqueEmailException{
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "delete from login where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();

			sql = "insert into login values (default, ?, ?, "
					+ "(select id from users where id = ?)"
					+ ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "update users set first_name = ?, last_name = ?, "
					+ "email = ?, role_id = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getFirstName());
			pstmt.setString(2, t.getLastName());
			pstmt.setString(3, t.getEmail());
			pstmt.setInt(4, t.getRole().getId());
			pstmt.setInt(5, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				if (e.getMessage().contains("username")) {
					throw new NonUniqueUsernameException();
				} else if (e.getMessage().contains("email")) {
					throw new NonUniqueEmailException();
				} else {
					e.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "delete from login where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Set<User> getUserInfo(String addString, Connection conn) throws SQLException {
		Set<User> users = new HashSet<>();
		String sql = "select u.id as user_id, u.first_name, u.last_name, u.email, "
				+ "l.username, l.psswrd, "
				+ "r.id as role_id, r.name as role_name "
				+ "from (users as u join login l on u.id = l.user_id) "
				+ "join roles as r on u.role_id  = r.id "
				+ addString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next() == false) {
			System.out.println("Query returned no data.");
		} else {
			do {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("psswrd"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				user.setRole(role);
				users.add(user);
			} while (rs.next());
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }		
		
		return users;
	}

}
