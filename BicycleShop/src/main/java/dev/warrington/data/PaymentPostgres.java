package dev.warrington.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.warrington.beans.Payment;
import dev.warrington.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment add(Payment t) {

		Payment p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into payments values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getCustomerId());
			pstmt.setInt(2, t.getBicycleId());
			pstmt.setDouble(3, t.getTotalOwed());
			pstmt.setDouble(4, t.getWeeklyPayment());
			pstmt.setInt(5, t.getPaymentsRemaining());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
		
	}

	@Override
	public Payment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Payment> getAll() {
		
		Set<Payment> payments = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Payment p;
			
			String sql = "select * from payments";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				p = new Payment(rs.getInt("customer_id"), rs.getInt("bike_id"), rs.getDouble("total_owed"), rs.getDouble("weekly_payment"), rs.getInt("payments_remaining"));
				p.setId(rs.getInt("id"));
				
				payments.add(p);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return payments;
		
	}

	@Override
	public void update(Payment p) {
		
		try (Connection conn = cu.getConnection()) {
			
			String sql = "update payments set total_owed = " + p.getTotalOwed() + ", payments_remaining = "
					+ p.getPaymentsRemaining() + " where id = " + p.getId();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void delete(Payment t) {
		
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from payments where id = " + t.getId();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Payment> getMine(Integer id) {
		
		Set<Payment> payments = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Payment p;
			
			String sql = "select * from payments where customer_id =" + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				p = new Payment(rs.getInt("customer_id"), rs.getInt("bike_id"), rs.getDouble("total_owed"), rs.getDouble("weekly_payment"), rs.getInt("payments_remaining"));
				p.setId(rs.getInt("id"));
				
				payments.add(p);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return payments;
		
	}

}
