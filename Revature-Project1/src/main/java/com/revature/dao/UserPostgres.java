package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public int registerAUser(String username, String password) {
		int theUsersId = -1;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into all_users values (default, ?, ?, 'author')";
			String[] keys = {"user_id"};
			PreparedStatement userEntrance = conn.prepareStatement(sql);
			
			userEntrance.setString(1, username);
			userEntrance.setString(2, password);
			userEntrance.executeUpdate();
			ResultSet rs = userEntrance.getGeneratedKeys();
			
			if (rs.next()) {
				theUsersId = rs.getInt(1);
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return theUsersId;
	}

	@Override
	public boolean removeAUser(int i) {
		Connection conn = cu.getConnection();
		
		try{
			conn.setAutoCommit(false);
			String sqlApproval = "delete from approvals where approvals.proposed_works_id = (select proposed_work_id from proposed_works where creator_id = ?)";
			String sqlWork = "delete from proposed_works where creator_id = ?";
			String sqlEditor = "delete from authors where author_id = ?";
			
			PreparedStatement pstmtA = conn.prepareStatement(sqlApproval);
			PreparedStatement pstmtW = conn.prepareStatement(sqlWork);
			PreparedStatement pstmtE = conn.prepareStatement(sqlEditor);
			
			pstmtA.setInt(1, i);
			pstmtW.setInt(1, i);
			pstmtE.setInt(1, i);
			
			
			
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			conn.close();
		}
		
		return false;
	}

	@Override
	public User verifyAUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
