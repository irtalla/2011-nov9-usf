package com.revature.data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;
import com.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();

	@Override
	public Payment getById(Integer id) {
		Payment retPayment = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select id, bicycle_id, amount from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			
			ResultSet rs= pstmt.getResultSet();
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setId(rs.getInt("id"));
				Bicycle bicycle = bicyclePostgres.getById(rs.getInt("bicycle_id"));
				payment.setBicycle(bicycle);
				payment.setAmount(rs.getDouble("amount"));
				retPayment = payment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retPayment;
	}

	@Override
	public Set<Payment> getAll() {
		Set<Payment> payments = new HashSet<Payment>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select id, bicycle_id, amount from payment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next()) {
				Payment retPayment = new Payment();
				retPayment.setId(rs.getInt("id"));
				Bicycle bicycle = bicyclePostgres.getById(rs.getInt("bicycle_id"));
				retPayment.setBicycle(bicycle);
				retPayment.setAmount(rs.getDouble("amount"));
				payments.add(retPayment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public void update(Payment payment) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update payment set bicycle_id = ?,  amount = ? "
					+ "where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, payment.getBicycle().getId());
			pstmt.setDouble(2, payment.getAmount());
			pstmt.setInt(3, payment.getId());
			
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Payment t) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sqlString = "delete from payment where id = ?";
			String[] keyStrings = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keyStrings);
			pstmt.setInt(1, t.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resetDefault();
	}

	@Override
	public Payment add(Payment p) {
		Payment retPayment = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "insert into payment values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keys);
			pstmt.setInt(1, p.getBicycle().getId());
			pstmt.setDouble(2, p.getAmount());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				retPayment = p;
				retPayment.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retPayment;
	}
	
	public void resetDefault() {
		try (Connection conn = cu.getConnection()){
			String sqlString = "select setval('bicycleshop.payment_id_seq', max(id)) FROM payment";
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
