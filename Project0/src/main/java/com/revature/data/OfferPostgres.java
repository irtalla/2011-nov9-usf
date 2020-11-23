package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
			pstmt.setInt(1, t.getCustomerId() );
			pstmt.setInt(2, t.getProductId() );
			pstmt.setInt(3, t.getStatus().getId() );
			pstmt.setDouble(4, t.getAmount() );

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
			String sql = "select "
						+ "	offer.id as offer_id, "
						+ " offer.customer_id as customer_id, "
						+ " offer.product_id as product_id, "
						+ " offer.amount as offer_amount, "
						+ " offer.status_id as status_id, "
						+ " status.name as status_name "
						+ "	from offer "
						+ "		join status "
						+ "		on offer.status_id = status.id "; 
								
			
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
		
		
		Offer returnedOffer = new Offer(); 
		
		returnedOffer.setId( rs.getInt("offer_id") );
		returnedOffer.setCustomerId( rs.getInt("customer_id") ); 
		returnedOffer.setProductId( rs.getInt("product_id") ); 
		returnedOffer.setAmount( rs.getDouble("offer_amount") );
		returnedOffer.getStatus().setId( rs.getInt("status_id") );
		returnedOffer.getStatus().setName( rs.getString("status_name"));
		
		return returnedOffer; 
	}
	
	/**
	 * Currently, only the status of an offer can be changed. This
	 * implies that a customer can make discrete offers for the 
	 * same product 
	 */
	@Override
	public boolean update(Offer t) {
		
		Integer rowsUpdated = 0; 
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " update offer "
							+ " set status_id = ? "
							+ " where id = ? ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getStatus().getId() );
			pstmt.setInt(2, t.getId() );

			rowsUpdated = pstmt.executeUpdate();
			
			
			if (rowsUpdated > 1) {
				// TODO throw error
				conn.rollback();
			} else {
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return rowsUpdated > 0 ? true : false;
	}

	@Override
	public boolean delete(Offer t) {
		
		Integer rowsUpdated = 0; 
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " delete from offer "
						+ " where "
						+ " offer.id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId() );

			rowsUpdated = pstmt.executeUpdate();
			
			if (rowsUpdated > 1) {
				// TODO throw error
				conn.rollback();
			} else {
				conn.commit();
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return rowsUpdated > 0 ? true : false;
	}

	@Override
	public Set<Offer> getOffersByStatus(String status) {
		
		Set<Offer> offers = new HashSet<Offer>();
		offers = this.getAll(); 
		offers.removeIf( offer -> ! offer.getStatus().getName().equalsIgnoreCase(status) );
		return offers;
	}

	@Override
	public Set<Offer> getOffersByCustomerId(Integer id) {
		
		Set<Offer> offers = new HashSet<Offer>();
		offers = this.getAll(); 
		offers.removeIf( offer -> offer.getCustomerId() != id );
		return offers;
	}

	@Override
	public Set<Offer> getOffersByProductId(Integer id) {
		
		Set<Offer> offers = new HashSet<Offer>();
		offers = this.getAll(); 
		offers.removeIf( offer -> offer.getCustomerId() != id );
		return offers;
	}
	
	
}
