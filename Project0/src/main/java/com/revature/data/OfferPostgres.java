package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.beans.Offer;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public OfferPostgres () {}

	@Override
	public Offer add(Offer t) {
		
		Offer c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getCustomerId());
			pstmt.setInt(2, t.getProductId() );
			pstmt.setDouble(3, t.getAmount());
			pstmt.setInt(4, t.getStatus().getId());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if ( rs.next() ) {
				c = t;
				c.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
		
		
	}

	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		
		Set<Offer> offers = new HashSet<Offer>(); 
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select"
					+ " offer.id as offer_id, "
					+ " offer.customer_id as customer_id, "
					+ " offer.product_id as product_id, "
					+ " offer.status_id as status_id, "
					+ " offer.amount as offer_amount "
					+ "from offer "; 
								
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(); 
			
			
			Offer returnedOffer = null;
			while ( rs.next() ) {
				
				try {
					returnedOffer = deserializeOffer(rs); 
					
					offers.add(returnedOffer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers; 
	}

	private Offer deserializeOffer(ResultSet rs) throws SQLException {
		
		
		// TODO : need features, and offers DAOs
		Offer returnedOffer = new Offer(); 
		
		returnedOffer.setId( rs.getInt("Offer_id") );
		returnedOffer.setCustomerId( rs.getInt("customer_id") ); 
		returnedOffer.setProductId( rs.getInt("product_id") ); 
		returnedOffer.getStatus().setId( rs.getInt("status_id") );
		returnedOffer.setAmount( rs.getDouble("offer_amount") );
		
		
		// TODO : get status name
		
		return returnedOffer; 
	}

	@Override
	public boolean update(Offer t) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Offer t) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Offer> getOffersByStatus(String status) {
		
		Set<Offer> offers = new HashSet<Offer>();
		offers = this.getAll(); 
		offers.removeIf( offer -> ! offer.getStatus().getName().equalsIgnoreCase(status) );
		return offers;
	}
}
