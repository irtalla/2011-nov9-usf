package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Rejection;
import com.revature.beans.Person;
import com.revature.util.ConnectionUtil;

public class RejectionPostgreSQL implements RejectionDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public Rejection add(Rejection r) {
		if(r == null) {
			System.out.println("Cannot add a null rejection to the database");
			return r;
		}
		else {
			//System.out.println(r);
		Rejection n = new Rejection();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into rejections values(default, default, ?, ?, ?, ?)";
			String[] keys = {"rejection_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  r.getReason());
			pstmt.setInt(2, r.getRejectorID());
			pstmt.setInt(3, r.getRejectedID());
			pstmt.setInt(4, r.getStoryRejectedID());
			
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
				n.setRejectionID(rs.getInt(1));
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				r.setRejectionDate(date);
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
	public Set<Rejection> getAll() {
		Set<Rejection> rejections = new HashSet<Rejection>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from rejections";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Rejection n = new Rejection();
				n.setRejectionID(rs.getInt(1));
				n.setRejectionDate(rs.getDate(2));
				n.setReason(rs.getString(3));
				n.setRejectorID(rs.getInt(4));
				n.setRejectedID(rs.getInt(5));
				n.setStoryRejectedID(rs.getInt(6));
				rejections.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rejections;
	}

	@Override
	public void update(Rejection r) {
		if(r == null) {
			System.out.println("Cannot update a null rejection");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update rejections "
						+" set rejection_date = default, "
						+" reason = ?, "
						+" rejector_id = ?, "
						+" rejected_id = ? "
						+" where rejection_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, r.getReason());
				pstmt.setInt(2, r.getRejectorID());
				pstmt.setInt(3, r.getRejectedID());
				pstmt.setInt(4, r.getRejectionID());
				
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
	public void delete(Rejection r) {

		if(r == null) {
			System.out.println("Cannot delete a null rejection from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from rejections "
						+" where rejection_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r.getRejectionID());
				
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
	public Set<Rejection> getByRejectedID(Integer id) {
		Set<Rejection> rejections = new HashSet<Rejection>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from rejections where rejected_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				//System.out.println("Got Here");
				Rejection n = new Rejection();
				n.setRejectionID(rs.getInt(1));
				n.setRejectionDate(rs.getDate(2));
				n.setReason(rs.getString(3));
				n.setRejectorID(rs.getInt(4));
				n.setRejectedID(rs.getInt(5));
				n.setStoryRejectedID(rs.getInt(6));
				rejections.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rejections;
	}

	@Override
	public Rejection getByRejectionID(Integer id) {
		Rejection n = new Rejection();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from rejections where rejection_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setRejectionID(rs.getInt(1));
				n.setRejectionDate(rs.getDate(2));
				n.setReason(rs.getString(3));
				n.setRejectorID(rs.getInt(4));
				n.setRejectedID(rs.getInt(5));
				n.setStoryRejectedID(rs.getInt(6));
			}
			else {
				return null;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	
	
}