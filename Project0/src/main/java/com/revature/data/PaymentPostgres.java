package com.revature.data;

import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;
import com.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private BicyclePostgres bicycleDAO = new BicyclePostgres();

	@Override
	public Payment add(Payment t){
		Payment p = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into payment values (default, ?, ?, ?)";
			String[] keys = {"payment_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getBicycle().getId());
			pstmt.setTimestamp(3, t.getTs());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Payment getById(Integer id) {
		Payment p = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select payment.payment_id, " + 
					"payment.bicycle_id, " + 
					"payment.amount, " + 
					"payment.ts " + 
					"from payment " +
					"where payment_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Payment();
				p.setId(id);
				p.setAmount(rs.getDouble("amount"));
				p.setTs(rs.getTimestamp("ts"));
				
				p.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Set<Payment> getAll() {
		Set<Payment> payments = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select payment.payment_id, " + 
					"payment.bicycle_id, " + 
					"payment.amount, " + 
					"payment.ts " + 
					"from payment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("payment_id"));
				p.setAmount(rs.getDouble("amount"));
				p.setTs(rs.getTimestamp("ts"));
				
				p.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				payments.add(p);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public void update(Payment t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update payment set " + 
			"payment.bicycle_id = ?, " + 
			"payment.amount = ?, " + 
			"payment.ts = ? "
			+ "where payment.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getBicycle().getId());
			pstmt.setDouble(2, t.getAmount());
			pstmt.setTimestamp(3, t.getTs());
			pstmt.setInt(4, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
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
	public void delete(Payment t) {
		try(Connection conn = cu.getConnection()){
			String sql = "delete from payment where payment_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Payment> getPaymentsByBicycle(Bicycle b) {
		Set<Payment> bicyclePayments = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select payment.payment_id, " + 
					"payment.bicycle_id, " + 
					"payment.amount, " + 
					"payment.ts " + 
					"from payment "
					+ "where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("payment_id"));
				p.setAmount(rs.getDouble("amount"));
				p.setTs(rs.getTimestamp("ts"));
				
				p.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				bicyclePayments.add(p);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return bicyclePayments;
	}

}
