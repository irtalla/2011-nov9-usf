package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.revature.beans.User;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public User add(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		User user = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
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
				role.setName(rs.getString("role_name"));
				user.setRole(role);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
