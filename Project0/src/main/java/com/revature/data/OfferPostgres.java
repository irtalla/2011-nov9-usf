package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.beans.Bike;
import com.revature.beans.Model;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer t) {
		// TODO Auto-generated method stub
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into Offer values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getUserId());
			pstmt.setInt(2, t.getBikeId());
			pstmt.setString(3, t.getOfferStatus());
			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				o = t;
				o.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		Offer o = null;

		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select * from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				o = new Offer();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setBikeId(rs.getInt("bike_id"));
				o.setOfferStatus(rs.getString("status"));
				
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public Set<Offer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Offer> getAvailableOffers() {
		Set<Offer> offers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer where status = 'Pending' ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setUserId(rs.getInt("user_id"));
				offer.setBikeId(rs.getInt("bike_id"));
				offer.setOfferStatus(rs.getString("status"));
				
				offers.add(offer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}
	
	@Override
	public void update(Offer t) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeOffer(Offer o) {
		// TODO Auto-generated method stub
		//nuke the offer, send him to planks yarrr!!
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			//update from pending to accepted
			String sql = "delete from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getId());
			pstmt.executeUpdate();
			conn.commit();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}


	@Override
	public void completeOffer(Offer o) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			//update from pending to accepted
			String sql = "update offer set status = 'Accepted' where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getId());
			pstmt.executeUpdate();
			conn.commit();
			
			
			// add user and bike
			sql = "insert into user_bike values ( ? , ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getUserId());
			pstmt.setInt(2, o.getBikeId());
			pstmt.executeUpdate();
			conn.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
