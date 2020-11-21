package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public User add(User t) {
		// TODO Auto-generated method stub\
		User u = null;

		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into user_acc values (default, ?, ?, ?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getFirstName());
			pstmt.setString(2, t.getLastName());
			pstmt.setString(3, t.getUsername());
			pstmt.setString(4, t.getPassword());
			pstmt.setInt(5, t.getRole().getId());
			

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
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		User user = null;

		try (Connection conn = cu.getConnection()) {
			String sql = "select * from user_acc where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pwd"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				//role.setName(rs.getString("role_name"));
				user.setRole(role);

				user.setBikes(getBikesByUserId(user.getId(), conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
		User user = null;

		try (Connection conn = cu.getConnection())
		{
			String sql = "select * from user_acc join user_role on user_role_id = user_role.id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("pwd"));
				Role role = new Role();
				role.setId(rs.getInt("user_role_id"));
				role.setName(rs.getString("name"));
				user.setRole(role);
				user.setBikes(getBikesByUserId(user.getId(), conn));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}
	
	private Set<Bike> getBikesByUserId(Integer id, Connection conn) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		BikeDAO bikeDao = new BikePostgres();
		
		String sql = "select * from user_bike where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike mybike = bikeDao.getById(rs.getInt("bike_id"));
			bikes.add(mybike);
		}
		
		return bikes;
	}

}
