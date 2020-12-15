package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.ChangeRequest;
import com.revature.beans.Person;
import com.revature.util.ConnectionUtil;

public class ChangeRequestPostgreSQL implements ChangeRequestDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public ChangeRequest add(ChangeRequest r) {
		if(r == null) {
			System.out.println("Cannot add a null request to the database");
			return r;
		}
		else {
			//System.out.println(r);
		ChangeRequest n = new ChangeRequest();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into change_requests values(default, default, ?, ?, ?, ?)";
			String[] keys = {"request_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  r.getMessage());
			pstmt.setInt(2,  r.getStoryID());
			pstmt.setInt(3,  r.getPersonRequestingID());
			pstmt.setInt(4,  r.getPersonRequestedID());
			
			try {
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("Exception here");
				System.out.println(e);
				return n;
				//e.printStackTrace();
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			//System.out.println("Right before the if - else statements");
			if(rs.next()) {
				n=r;
				n.setRequestID(rs.getInt(1));
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				n.setRequestDate(date);
				conn.commit();
			}
			else {
				System.out.println("got to the else statement");
				conn.rollback();
			}
			
			}
			catch(Exception e) {
				System.out.println("Exception cought");
			e.printStackTrace();
			}
			return n;
		}
	}

	@Override
	public Set<ChangeRequest> getAll() {
		Set<ChangeRequest> requests = new HashSet<ChangeRequest>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from change_requests";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				ChangeRequest n = new ChangeRequest();
				n.setRequestID(rs.getInt(1));
				n.setRequestDate(rs.getDate(2));
				n.setMessage(rs.getString(3));
				n.setStoryID(rs.getInt(4));
				n.setPersonRequestingID(rs.getInt(5));
				requests.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public void update(ChangeRequest r) {
		if(r == null) {
			System.out.println("Cannot update a null request");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update change_requests "
						+" set message = ?, "
						+" story_id = ?, "
						+" person_requesting = ? "
						+" where request_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, r.getMessage());
				pstmt.setInt(2, r.getStoryID());
				pstmt.setInt(3, r.getPersonRequestingID());
				pstmt.setInt(4, r.getRequestID());
				
				int n = pstmt.executeUpdate();
				
				if(n > 0) {
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
	public void delete(ChangeRequest r) {

		if(r == null) {
			System.out.println("Cannot delete a null person from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from change_requests "
						+" where request_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r.getRequestID());
				
				int nr = pstmt.executeUpdate();
				
				if(nr > 0) {
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
	public Set<ChangeRequest> getByRequestedID(Integer id) {
		Set<ChangeRequest> requests = new HashSet<ChangeRequest>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from change_requests where person_requested = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				ChangeRequest n = new ChangeRequest();
				n.setRequestID(rs.getInt(1));
				n.setRequestDate(rs.getDate(2));
				n.setMessage(rs.getString(3));
				n.setStoryID(rs.getInt(4));
				n.setPersonRequestingID(rs.getInt(5));
				requests.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public ChangeRequest getByRequestID(Integer id) {
		ChangeRequest n = new ChangeRequest();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from change_requests where request_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setRequestID(rs.getInt(1));
				n.setRequestDate(rs.getDate(2));
				n.setMessage(rs.getString(3));
				n.setStoryID(rs.getInt(4));
				n.setPersonRequestingID(rs.getInt(5));
				n.setPersonRequestedID(rs.getInt(6));
				conn.commit();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
}
