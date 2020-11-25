package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer t) {
	Offer o = null;
	
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);
		String sql = "insert into offer values (default, ?, ?, ?, 'pending')";
		String[] keys = {"id"};
		PreparedStatement pstmt = conn.prepareStatement(sql, keys);
		pstmt.setInt(1, t.getPersonId());
		pstmt.setInt(2, t.getBikeId());
		pstmt.setFloat(3, t.getPrice());

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
	//	Offer o = null;
		Offer offer = new Offer();
		
		try (Connection conn = cu.getConnection()) {
	
			String sql = "select * from offer where id =?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
	//		ResultSet rs = pstmt.getGeneratedKeys();
			ResultSet set = pstmt.executeQuery();
            
			while (set.next()) {
			    offer.setId(set.getInt(1));
				offer.setPersonId(set.getInt(2));
				offer.setBikeId(set.getInt(3));
			    offer.setPrice(set.getFloat(4));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	}
		return offer;
	}

	@Override
	public Set<Offer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(Offer t, Bike b) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "update offer set accept =1 where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
	

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
		
	//	return o;
		
	}
	
	@Override
	public void reject(Offer t, Bike b) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "update offer set accept =0 where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
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
		
	
		
	}

	@Override
	public void deleteOfferForBikeId(Integer id) {
		
		
		Offer o = null;
		Offer offer = new Offer();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from offer where bike_id =? and accept <> 'accepted'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
	}
	}
	@Override
	public Set<Offer> getAcceptedOffers() {
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "SELECT * from offer where accept='accepted'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setPersonId(rs.getInt("person_id"));
				offer.setBikeId(rs.getInt("bike_id"));
				offer.setPrice(rs.getFloat("price"));
				offer.setAccept(rs.getString("accept"));
				offers.add(offer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getAvailableOffers() {
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "SELECT * from offer where accept<>'accepted'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setPersonId(rs.getInt("person_id"));
				offer.setBikeId(rs.getInt("bike_id"));
				offer.setPrice(rs.getFloat("price"));
				offer.setAccept(rs.getString("accept"));
				offers.add(offer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public void update(Offer t) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offer set accept = ? where id =?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
	        pstmt.setString(1, t.getAccept());
			pstmt.setInt(2, t.getId());

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
		
	}

	@Override
	public void delete(Offer t) {
	Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from offer where id =?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
	
			pstmt.setInt(1, t.getId());

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
		
	}
	
}
