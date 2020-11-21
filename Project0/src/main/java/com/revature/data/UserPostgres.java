package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;


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
		return null;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
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

}
