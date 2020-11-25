package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.Payment;
import com.revature.models.PaymentStatus;
import com.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment add(Payment t) {
		Payment p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into payment values "
					+ "(default, ?, "
					+ "(select id from payment_status where id = ?)"
					+ ")";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setFloat(1, t.getValue());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	@Override
	public Payment getById(Integer id) {
		Payment p = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where p.id = " + id;
			Set<Payment> payments = getPaymentInfo(sql, conn);
			for (Payment payment: payments) {
				if (payment.getId().equals(id)) {
					p = payment;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	@Override
	public Set<Payment> getByStatus(String status) {
		Set<Payment> payments = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select id from payment_status where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			Integer statusId = 0;
			if (rs.next()) statusId = rs.getInt("id");
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
			sql = "where ps.id = " + statusId;
			payments = getPaymentInfo(sql, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return payments;
	}
	
	@Override
	public Set<Payment> getAll() {
		Set<Payment> payments = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			payments = getPaymentInfo(sql, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return payments;
	}

	@Override
	public void update(Payment t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "update payment set value = ?, "
					+ "status_id = (select ps.id from payment_status as ps where ps.id = ?)"
					+ "where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getValue());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.setInt(3, t.getId());
			rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Payment t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Set<Payment> getPaymentInfo(String additionalString, Connection conn) throws SQLException {
		Set<Payment> payments = new HashSet<>();
		String sql = "select p.id as payment_id, p.value, "
				+ "ps.id as status_id, ps.name as status_name "
				+ "from payment as p join payment_status as ps on p.status_id = ps.id "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Payment payment = new Payment();
			payment.setId(rs.getInt("payment_id"));
			payment.setValue(rs.getFloat("value"));
			PaymentStatus ps = new PaymentStatus();
			ps.setId(rs.getInt("status_id"));
			ps.setName(rs.getString("status_name"));
			payment.setStatus(ps);
			payments.add(payment);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return payments;
		
	}
	
}
