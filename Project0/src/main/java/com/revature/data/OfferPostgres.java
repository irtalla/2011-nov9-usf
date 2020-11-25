package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.Offer;
import com.revature.models.OfferStatus;
import com.revature.models.Payment;
import com.revature.models.PaymentStatus;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer t) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?,"
					+ "(select i.id from inventory as i where i.id = ?), "
					+ "(select os.id from offer_status as os where os.id = ?)"
					+ ")";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setFloat(1, t.getValue());
			pstmt.setInt(2, t.getItemId());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				o = t;
				o.setId(rs.getInt(1));
				
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
		
	}

	@Override
	public Offer getById(Integer id) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where o.id = " + id;

			Set<Offer> offers = getOfferInfo(sql, conn);
			for (Offer offer: offers) {
				if (offer.getId().equals(id)) {
					o = offer;
				}
			}
				
			sql = "where offer_id = " + id;
			Set<Payment> payments = getPaymentInfo(sql, conn);
			o.setPayments(payments);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
	}
	
	@Override
	public Set<Offer> getOfferByCustomer(Integer id) {
		Set<Offer> offers = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "join users_offer on offer.id = customer_offer.offer_id "
					+ "where customer_offer.user_id = " + id;
			offers = getOfferInfo(sql, conn);
			for (Offer offer: offers) {
				sql = "where offer_id  = " + id;
				Set<Payment> payments = getPaymentInfo(sql, conn);
				offer.setPayments(payments);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getOfferByEmployee(Integer id) {
		Set<Offer> offers = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "join employee_offer as eo on offer.id = eo.offer_id "
					+ "where eo.user_id = " + id;
			offers = getOfferInfo(sql, conn);
			for (Offer offer: offers) {
				sql = "where offer_id  = " + offer.getId();
				Set<Payment> payments = getPaymentInfo(sql, conn);
				offer.setPayments(payments);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getOfferByStatus(String status) {
		Set<Offer> offers = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select id from payment_status where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			Integer statusId = 0;
			if (rs.next()) statusId = rs.getInt("id");
			
			sql = "where ps.id = " + statusId;
			offers = getOfferInfo(sql, conn);
			for (Offer offer: offers) {
				sql = "where offer_id  = " + offer.getId();
				Set<Payment> payments = getPaymentInfo(sql, conn);
				offer.setPayments(payments);
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}
	
	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			offers = getOfferInfo(sql, conn);
			for (Offer offer: offers) {
				sql = "where offer_id  = " + offer.getId();
				Set<Payment> payments = getPaymentInfo(sql, conn);
				offer.setPayments(payments);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "delete from offer_payment where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected = pstmt.executeUpdate();
			
			for (Payment p : t.getPayments()) {
				sql = "insert into offer_payment values ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.setInt(2, p.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			sql = "update offer set item_id = ?, value = ?, status_id = ?"
					+ "where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getItemId());
			pstmt.setFloat(2, t.getValue());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getId());
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
	public void delete(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from customer_offer where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			sql = "delete from employee_offer where offer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from offer_payment where offer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			for (Payment p : t.getPayments()) {
				sql = "delete from payment where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			sql = "delete from offer where offer.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
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

	private Set<Offer> getOfferInfo(String additionalString, Connection conn) throws SQLException {
		Set<Offer> offers = new HashSet<>();
		String sql = "select o.id as offer_id, value as offer_value, item_id, "
				+ "os.id as status_id, os.name as status_name "
				+ "from offer as o join offer_status as os on o.status_id = os.id "	
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Offer offer = new Offer();
			offer.setId(rs.getInt("offer_id"));
			offer.setItemId(rs.getInt("item_id"));
			offer.setValue(rs.getFloat("offer_value"));
			OfferStatus os = new OfferStatus();
			os.setId(rs.getInt("status_id"));
			os.setName(rs.getString("status_name"));
			offer.setStatus(os);
			offers.add(offer);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return offers;
		
	}
	
	private Set<Payment> getPaymentInfo(String additionalString, Connection conn) throws SQLException {
		Set<Payment> payments = new HashSet<>();
		PaymentDAO pDao = new PaymentPostgres();
		
		String sql = "select payment_id from offer_payment "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Payment payment = pDao.getById(rs.getInt(1));
			payments.add(payment);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return payments;
		
	}
}
