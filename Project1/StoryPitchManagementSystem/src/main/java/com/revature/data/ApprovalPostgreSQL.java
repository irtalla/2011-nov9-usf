package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.Person;
import com.revature.util.ConnectionUtil;

public class ApprovalPostgreSQL implements ApprovalDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public Approval add(Approval a) {
		if(a == null) {
			System.out.println("Cannot add a null approval to the database");
			return a;
		}
		else {
			//System.out.println(a);
		Approval n = new Approval();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into approvals values(default, default, ?, ?, ?, ?)";
			String[] keys = {"approval_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setInt(1,  a.getStatusApproved());
			pstmt.setInt(2, a.getApproverID());
			pstmt.setInt(3, a.getApprovedID());
			pstmt.setInt(4, a.getStoryApprovedID());
			
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
				n=a;
				n.setApprovalID(rs.getInt(1));
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				a.setApprovalDate(date);
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
	public Set<Approval> getAll() {
		Set<Approval> approvals = new HashSet<Approval>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from approvals";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Approval n = new Approval();
				n.setApprovalID(rs.getInt(1));
				n.setApprovalDate(rs.getDate(2));
				n.setStatusApproved(rs.getInt(3));
				n.setApproverID(rs.getInt(4));
				n.setApprovedID(rs.getInt(5));
				n.setStoryApprovedID(rs.getInt(6));
				approvals.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return approvals;
	}

	@Override
	public void update(Approval a) {
		if(a == null) {
			System.out.println("Cannot update a null approval");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update approvals "
						+" set approval_date = default, "
						+"  status_approved = ?, "
						+" approver_id = ?, "
						+" approved_id = ? "
						+" where approval_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a.getStatusApproved());
				pstmt.setInt(2, a.getApproverID());
				pstmt.setInt(3, a.getApprovedID());
				pstmt.setInt(4, a.getApprovalID());
				
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
	public void delete(Approval a) {

		if(a == null) {
			System.out.println("Cannot delete a null approval from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from approvals "
						+" where approval_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a.getApprovalID());
				
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
	public Set<Approval> getByApprovedID(Integer id) {
		Set<Approval> approvals = new HashSet<Approval>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from approvals where approved_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Approval n = new Approval();
				n.setApprovalID(rs.getInt(1));
				n.setApprovalDate(rs.getDate(2));
				n.setStatusApproved(rs.getInt(3));
				n.setApproverID(rs.getInt(4));
				n.setApprovedID(rs.getInt(5));
				n.setStoryApprovedID(rs.getInt(6));
				approvals.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return approvals;
	}

	@Override
	public Approval getByApprovalID(Integer id) {
		Approval n = new Approval();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from approvals where approval_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setApprovalID(rs.getInt(1));
				n.setApprovalDate(rs.getDate(2));
				n.setStatusApproved(rs.getInt(3));
				n.setApproverID(rs.getInt(4));
				n.setApprovedID(rs.getInt(5));
				n.setStoryApprovedID(rs.getInt(6));
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