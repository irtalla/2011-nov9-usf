package com.revature.data;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.postgresql.util.PSQLException;

public class UserPostgreSQL implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public User add(User u) {
		if(u == null) {
			System.out.println("Cannot add a null user to the database");
			return u;
		}
		else {
		User n = new User();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into users values(default, ?, ?, ?, ?, 0)";
			String[] keys = {"user_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  u.getUsername());
			pstmt.setString(2,  u.getPassword());
			pstmt.setInt(3,  u.getRoleID());
			pstmt.setDouble(4,  u.getAccountBalance());
			
			try {
				pstmt.executeUpdate();
			} catch (PSQLException e) {
				return n;
				//e.printStackTrace();
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				n=u;
				n.setUserID(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			return n;
		}
	}

	@Override
	public User getById(Integer id) {
		User n = new User();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from users where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  id);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setUserID(rs.getInt(1));
				n.setUsername(rs.getString(2));
				n.setPassword(rs.getString(3));
				n.setRoleID(rs.getInt(4));
				n.setAccountBalance(rs.getDouble(5));
				n.setPaymentsRemaining(rs.getInt(6));
				conn.commit();
			}
			else {
				n = null;
				conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Set<User> getAll() {
		Set<User> users = new HashSet<User>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from users order by user_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				User u = new User();
				u.setUserID(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setRoleID(rs.getInt(4));
				u.setAccountBalance(rs.getDouble(5));
				u.setPaymentsRemaining(rs.getInt(6));
				users.add(u);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void delete(User t) {
		if(t == null) {
			System.out.println("Cannot delete a null user from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				//first delete offers which the user has submitted
				String sql = "delete from offers where user_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getUserID());
				pstmt.executeUpdate();
				// then remove the user's id from any purchases they have made
				String sql2 = "update purchases "
						+" set user_id = null"
						+" where user_id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, t.getUserID());
				pstmt2.executeUpdate();
				// finally delete user from users table
				String sql3 = "delete from users "
						+" where user_id = ?";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, t.getUserID());
				int r = pstmt3.executeUpdate();
				
				if(r > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(User t) {
		if(t == null) {
			System.out.println("Cannot update a null user from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update users "
						+" set password = ?, "
						+" role_id = ?, "
						+" account_balance = ?, "
						+" payments_remaining = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getPassword());
				pstmt.setInt(2, t.getRoleID());
				pstmt.setDouble(3, t.getAccountBalance());
				pstmt.setInt(4, t.getPaymentsRemaining());
				
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public User getByUsername(String username) {
		User u = null;
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from users where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  username);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				User n = new User();
				n.setUserID(rs.getInt(1));
				n.setUsername(rs.getString(2));
				n.setPassword(rs.getString(3));
				n.setRoleID(rs.getInt(4));
				n.setAccountBalance(rs.getDouble(5));
				n.setPaymentsRemaining(rs.getInt(6));
				conn.commit();
				u = n;
			}
			else {
				conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
}
