package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;
import spencer.revature.beans.Payment;
import spencer.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	public Payment add(Payment t) {
		Payment pay=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into payment values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setDouble(1, t.getPayment());
			pstmt.setInt(2, t.getOffer().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				pay = t;
				pay.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pay;
	}
	public Set<Payment> getAll() {
		Set<Payment> pays = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select payment.id, payamount, offer_id, amount from payment join offer on offer_id=offer.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Payment pay = new Payment();
				pay.setId(rs.getInt("id"));
				pay.setPayment(rs.getDouble("payamount"));
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				pay.setOffer(o);
				
				pays.add(pay);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pays;
	}
	public Payment getById(Integer id) {
		Payment pay=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select payment.id, payamount, offer_id, amount from payment join offer on offer_id=offer.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				pay = new Payment();
				pay.setId(rs.getInt("id"));
				pay.setPayment(rs.getDouble("payamount"));
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				pay.setOffer(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pay;
	}

	public void update(Payment t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Payment t) {
		// TODO Auto-generated method stub
		
	}

	

}
