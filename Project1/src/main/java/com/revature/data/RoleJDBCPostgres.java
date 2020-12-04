package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.NonUniqueRoleException;
import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class RoleJDBCPostgres implements RoleDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Role add(Role t) throws NonUniqueRoleException {
		Role r = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into roles values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				r = t;
				r.setId(rs.getInt(1));
				
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueRoleException();
			}
			e.printStackTrace();
		}
		
		return r;
	}
	
	@Override
	public Role getById(Integer id) {
		Role r = null;
		
		try (Connection conn = cu.getConnection()){
			String sql = "where id = " + id;
			Set<Role> roles = getRoleInfo(sql, conn);
			for (Role role : roles) {
				if (role.getId().equals(id)) {
					r = role;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public Role getByName(String name) {
		Role r = null;
		
		try (Connection conn = cu.getConnection()){
			String sql = "where name = '" + name + "'";
			Set<Role> roles = getRoleInfo(sql, conn);
			for (Role role : roles) {
				if (role.getName().equals(name)) {
					r = role;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	@Override
	public Set<Role> getAll() {
		Set<Role> roles = null;
		
		try (Connection conn = cu.getConnection()){
			String sql = "";
			roles = getRoleInfo(sql, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public void update(Role t) throws NonUniqueRoleException{
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "update roles set name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }	

		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueRoleException();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Role t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "delete from roles where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
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
	
	private Set<Role> getRoleInfo(String addString, Connection conn) throws SQLException{
		Set<Role> roles = new HashSet<>();
		
		String sql = "select id, name from roles "
				+ addString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next() == false) {
			System.out.println("Query returned no data");
		} else {
			do {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				roles.add(role);
			} while (rs.next());
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }	
		
		return roles;
	}

}
