package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import com.revature.util.ConnectionUtil;
import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.Payment;
import com.revature.beans.Purchase;

public class PaymentPostgreSQL implements PaymentDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment addPayment(Payment p) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into payments values "
					+ " (default, ?, ?)" ;
			String[] keys = {"payment_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			pstmt.setDouble(1,  p.getAmount());
			pstmt.setInt(2,  p.getUserId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			String sql3 = "select account_balance "
					+ " from users "
					+ " where user_id = ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1,  p.getUserId());
			
			ResultSet rs3 = pstmt3.executeQuery();
			double currentBalance = 0.0;
			if(rs3.next()) {
				//System.out.println("Got here");
				currentBalance = rs3.getDouble(1);
			}
			
			//Automatically updates user's account balance
			String sql2 = "update users "
					+ " set account_balance = ? "
					+ " where user_id = ?" ;
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setDouble(1,  (currentBalance - p.getAmount()));
			pstmt2.setInt(2,  p.getUserId());
			
			int r = pstmt2.executeUpdate();
			ResultSet rs2 = pstmt.getGeneratedKeys();

			//System.out.println(rs.next());
			//System.out.println(r > 0);
			//System.out.println(rs.next() && r > 0);
			if(rs.next() && r > 0) {
				//System.out.println("Got here too");
				p.setPaymentId(rs.getInt(1));
				conn.commit();
				return p;
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deletePayment(Payment p) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from payments "
					+ " where payment_id = ?" ;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  p.getPaymentId());
			
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

	@Override
	public Set<Payment> getByUserId(Integer id) {
		Set<Payment> payments = new HashSet<Payment>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * "
						+ " from payments p" 
						+ " where p.user_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Payment p = new Payment();
					p.setPaymentId(rs.getInt(1));
					p.setUserId(rs.getInt(3));
					p.setAmount(rs.getDouble(2));
					payments.add(p);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return payments;
	}

	@Override
	public Set<Payment> getAll() {
		Set<Payment> payments = new HashSet<Payment>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * "
						+ " from payments p";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Payment p = new Payment();
					p.setPaymentId(rs.getInt(1));
					p.setUserId(rs.getInt(3));
					p.setAmount(rs.getDouble(2));
					payments.add(p);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return payments;
	}

	@Override
	public Payment getByPaymentId(Integer id) {
		Payment p = new Payment();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * "
						+ " from payments p" 
						+ " where p.payment_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				if(rs.next()) {
					p.setPaymentId(id);
					p.setAmount(rs.getDouble(2));
					p.setUserId(rs.getInt(3));
					return p;
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}

}
