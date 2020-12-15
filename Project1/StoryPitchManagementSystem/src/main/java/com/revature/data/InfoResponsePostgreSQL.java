package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.InfoResponse;
import com.revature.util.ConnectionUtil;

public class InfoResponsePostgreSQL implements InfoResponseDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public InfoResponse add(InfoResponse r) {
		if(r == null) {
			System.out.println("Cannot add a null response to the database");
			return r;
		}
		else {
			//System.out.println(r);
		InfoResponse n = new InfoResponse();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into info_responses values(default, default, ?, ?, ?, ?, ?)";
			String[] keys = {"response_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			pstmt.setInt(1,  r.getRequestID());
			pstmt.setString(2,  r.getMessage());
			pstmt.setInt(3,  r.getStoryID());
			pstmt.setInt(4,  r.getPersonRespondingID());
			pstmt.setInt(5,  r.getPersonRespondedID());
			
			try {
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("Exception here");
				System.out.println(e);
				return n;
				//e.printStackTrace();
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				n=r;
				n.setResponseID(rs.getInt(1));
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				n.setResponseDate(date);
				conn.commit();
			}
			else {
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
	public Set<InfoResponse> getAll() {
		Set<InfoResponse> responses = new HashSet<InfoResponse>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from info_responses";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				InfoResponse n = new InfoResponse();
				n.setResponseID(rs.getInt(1));
				n.setResponseDate(rs.getDate(2));
				n.setRequestID(rs.getInt(3));
				n.setMessage(rs.getString(4));
				n.setStoryID(rs.getInt(5));
				n.setPersonRespondingID(rs.getInt(6));
				n.setPersonRespondedID(rs.getInt(7));
				responses.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return responses;
	}

	@Override
	public void update(InfoResponse r) {
		if(r == null) {
			System.out.println("Cannot update a null response");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update info_responses "
						+" set message = ?, "
						+" story_id = ?, "
						+" person_responding = ?, "
						+" person_responded = ? "
						+" where response_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, r.getMessage());
				pstmt.setInt(2, r.getStoryID());
				pstmt.setInt(3, r.getPersonRespondingID());
				pstmt.setInt(4, r.getPersonRespondedID());
				pstmt.setInt(5, r.getResponseID());
				
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
	public void delete(InfoResponse r) {

		if(r == null) {
			System.out.println("Cannot delete a null response from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from info_responses "
						+" where response_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r.getResponseID());
				
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
	public Set<InfoResponse> getByRespondedID(Integer id) {
		Set<InfoResponse> responses = new HashSet<InfoResponse>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from info_responses where person_responded = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				InfoResponse n = new InfoResponse();
				n.setResponseID(rs.getInt(1));
				n.setResponseDate(rs.getDate(2));
				n.setRequestID(rs.getInt(3));
				n.setMessage(rs.getString(4));
				n.setStoryID(rs.getInt(5));
				n.setPersonRespondingID(rs.getInt(6));
				n.setPersonRespondedID(rs.getInt(7));
				responses.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return responses;
	}

	@Override
	public InfoResponse getByResponseID(Integer id) {
		InfoResponse n = new InfoResponse();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from info_responses where response_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setResponseID(rs.getInt(1));
				n.setResponseDate(rs.getDate(2));
				n.setRequestID(rs.getInt(3));
				n.setMessage(rs.getString(4));
				n.setStoryID(rs.getInt(5));
				n.setPersonRespondingID(rs.getInt(6));
				n.setPersonRespondedID(rs.getInt(7));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Set<InfoResponse> getByRespondingID(Integer id) {
		Set<InfoResponse> responses = new HashSet<InfoResponse>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from info_responses where person_responding = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				InfoResponse n = new InfoResponse();
				n.setResponseID(rs.getInt(1));
				n.setResponseDate(rs.getDate(2));
				n.setRequestID(rs.getInt(3));
				n.setMessage(rs.getString(4));
				n.setStoryID(rs.getInt(5));
				n.setPersonRespondingID(rs.getInt(6));
				n.setPersonRespondedID(rs.getInt(7));
				responses.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return responses;
	}
	
}
