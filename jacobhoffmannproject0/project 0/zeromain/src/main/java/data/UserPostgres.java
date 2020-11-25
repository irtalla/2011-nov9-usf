package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import exceptions.NonUniqueUsernameException;
import models.Bike;
import models.Model;
import models.Status;
import models.User;
import utils.ConnectionUtil;

public class UserPostgres implements UserDao{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	public User add(User u) throws NonUniqueUsernameException {
		User t = new User();
	
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into users values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"user_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, u.getFname());
			pstmt.setString(2, u.getLname());
			pstmt.setString(3, u.getUsername());
			pstmt.setString(4, u.getPassword());
			pstmt.setBoolean(5, u.isIsEmployee());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				u = t;
				u.setUserId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
		
	}catch (Exception e) {
		if (e.getMessage().contains("violates unique constraint")) {
			throw new NonUniqueUsernameException();
		}
		e.printStackTrace();
	}	return t;
	}
	
	public User getByUsername(String username) {
	User human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select user_id, fname, lname,username,passwords,is_empolyee "
					+ "from users "
					+ " where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new User();
				human.setUsername(rs.getString("username"));
				human.setUserId(rs.getInt("user_id"));
				human.setPassword(rs.getString("passwords"));
				human.setIsEmployee(rs.getBoolean("is_Empolyee"));
				human.setFname(rs.getString("fname"));
				human.setFname(rs.getString("lname"));
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;	
	}

	@Override
	public User getById(Integer id) {
	User human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select user_id, fname, lname,username,passwords,is_empolyee "
					+ "from users "
					+ " where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new User();
				human.setUsername(rs.getString("username"));
				human.setUserId(rs.getInt("user_id"));
				human.setPassword(rs.getString("passwords"));
				human.setIsEmployee(rs.getBoolean("is_Empolyee"));
				human.setFname(rs.getString("fname"));
				human.setFname(rs.getString("lname"));
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;	
	}

	@Override
	public Set<User> getAll() {
		User human = new User();
		Set<User> users = new HashSet<>();
		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select user_id, fname, lname,username,passwords,is_empolyee "
					+ "from users ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				human = new User();
				human.setUsername(rs.getString("username"));
				human.setUserId(rs.getInt("user_id"));
				human.setPassword(rs.getString("passwords"));
				human.setIsEmployee(rs.getBoolean("is_Empolyee"));
				human.setFname(rs.getString("fname"));
				human.setFname(rs.getString("lname"));
				users.add(human);
			}
		}catch(Exception e) {
			e.printStackTrace();

		}
		return users;
	}

	@Override
	public void update(User t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update users set fname = ?, lname = ?,  username = ?, passwords = ?, is_empolyee = ? where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getFname());
			pstmt.setString(2, t.getLname());
			pstmt.setString(3, t.getUsername());
			pstmt.setString(4, t.getPassword());
			pstmt.setBoolean(5, t.isIsEmployee());
			pstmt.setInt(6, t.getUserId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(User t) {
		try (Connection conn = cu.getConnection()) {
			
			// then, we can delete the cat
			String sql = "delete from users where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getUserId());
			pstmt.executeUpdate();
				}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	}
