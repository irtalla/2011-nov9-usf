package com.revature.data;

import java.util.Set;
import java.util.HashSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.revature.beans.Person;
import com.revature.beans.Request;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class RequestPostgres implements RequestDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	//	t is generic
	@Override
	public Request add(Request t) throws NonUniqueUsernameException {
		Request r = null;
		
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into requests values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"request_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getEventType());
			pstmt.setString(3, t.getEventDescription());
			pstmt.setDouble(4, t.getAmount());
			pstmt.setString(5, t.getGradeFormat());
			pstmt.setString(6, t.getGradePres());	
			//	1 corresponds to pending
			pstmt.setInt(7, t.getStatus().getId());
			pstmt.setString(8, t.getReason());
			pstmt.setString(9, t.getAddInfo());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				r = t;
				r.setId(rs.getInt(1));
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
		
		return r;

		
	}
	
	

	@Override
	public Set<Request> getRequestByUsername(String userame) {			
		Set<Request> requests = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			//conn.setAutoCommit(false);
			String sql = "select * from requests where username=?"; 
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userame);
			ResultSet rs = pstmt.executeQuery();
			//if(rs.next())
			
			while (rs.next()) {
				Request request = new Request();	
				
				request.setEventType(rs.getString("event_type"));
				request.setEventDescription(rs.getString("event_desc"));
				request.setAmount(rs.getDouble("amount"));
				request.setGradeFormat(rs.getString("grade_format"));
				Status status = new Status();
				status.setId(rs.getInt("request_status_id"));
				requests.add(request);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requests;
		
	}

	@Override
	public void updateRequest(Request r) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update requests set request_status_id = ? where request_id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getStatus().getId());
			pstmt.setInt(2, r.getId());			
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Request> getAll() {
		Set<Request> requests = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select request_id, username, event_type, event_desc, amount, grade_format, grade_pres, reason, add_info, request_status_id, status_name from requests join request_status on request_status_id = request_status.id where status_name='pending'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Request request = new Request();
				
				request.setId(rs.getInt("request_id"));
				request.setUsername(rs.getString("username"));
				request.setEventType(rs.getString("event_type"));
				request.setAmount(rs.getDouble("amount"));
				request.setGradeFormat(rs.getString("grade_format"));
				request.setGradePres(rs.getString("grade_pres"));
				Status status = new Status();
				status.setId(rs.getInt("request_status_id"));
				status.setName(rs.getString("status_name"));
				request.setStatus(status);
				request.setReason(rs.getString("reason"));
				request.setAddInfo(rs.getString("add_info"));				
				
				requests.add(request);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}

	@Override
	public Request getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Request t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Request t) {
		// TODO Auto-generated method stub
		
	}
		
}


