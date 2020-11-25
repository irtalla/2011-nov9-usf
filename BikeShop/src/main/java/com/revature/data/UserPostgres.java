package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public User getById(Integer id) {
		User user = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select users.user_id as user_id, roles.role_id as role_id, username, passw, "
					+ "roles.role_name as role_name from users join roles on users.role_id = roles.role_id "
					+ "where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passw"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				user.setRole(role);
				user.setBikes(getBikesByUserId(user.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public Set<User> getAll() {
		Set<User> users = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select users.user_id as user_id, roles.role_id as role_id, username, passw, "
					+ "roles.role_name as role_name from users join roles on users.role_id = roles.role_id";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				User human = new User();
				human.setId(rs.getInt("user_id"));
				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("passw"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				human.setBikes(getBikesByUserId(human.getId()));
				
				users.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}

	public void delete(User t) {
		try (Connection conn =  cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from users where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public User add(User t) throws NonUniqueUsernameException {
			User u = null;
		
			try (Connection conn = cu.getConnection()) {
				conn.setAutoCommit(false);
				String sql = "insert into users values (default, ?, ?, ?)";
				String[] keys = {"user_id"};
				PreparedStatement pstmt = conn.prepareStatement(sql, keys);
				pstmt.setString(1, t.getUsername());
				pstmt.setInt(2, t.getRole().getId());
				pstmt.setString(3, t.getPassword());
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
					u = t;
					u.setId(rs.getInt(1));
					conn.commit();
				} else {
					conn.rollback();
				}
				
			} catch (Exception e) {
				if (e.getMessage().contains("violates unique constraint")) {
					throw new NonUniqueUsernameException();
				}
				e.printStackTrace();
			}
			
			return u;
	}

	public User getByUsername(String username) {
		User human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select users.user_id as user_id, roles.role_id as role_id, username, passw, "
					+ "roles.role_name as role_name from users "
					+ "join roles on users.role_id = roles.role_id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new User();
				human.setUsername(rs.getString("username"));
				human.setId(rs.getInt("user_id"));
				human.setPassword(rs.getString("passw"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				human.setBikes(getBikesByUserId(human.getId()));
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;
	}
	
	public Set<Bike> getBikesByUserId(Integer id) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection())
		{
		
		BikeDAO bikeDao = new BikePostgres();
		
		String sql = "select * from bikes where owner_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike ownedBike = bikeDao.getById(rs.getInt("bike_id"));
			bikes.add(ownedBike);
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bikes;
		
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

}
