package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer getById(Integer id) {
		Offer offer = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offers where offer_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				offer = new Offer();
				offer.setId(rs.getInt("offer_id"));
				if (rs.getString("amount") != null){
					String amountTemp = rs.getString("amount");
					offer.setAmount(Double.valueOf(amountTemp));
				}
				Bike b = new Bike();
				BikePostgres newBike = new BikePostgres();
				b = newBike.getById(rs.getInt("bike_id"));
				offer.setBike(b);
				User u = new User();
				UserPostgres newUser = new UserPostgres();
				u = newUser.getById(rs.getInt("buyer_id"));
				offer.setUser(u);
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
			
			return offer;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offers";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("offer_id"));
				if (rs.getString("amount") != null){
					String amountTemp = rs.getString("amount");
					offer.setAmount(Double.valueOf(amountTemp));
				}
				Bike b = new Bike();
				BikePostgres newBike = new BikePostgres();
				b = newBike.getById(rs.getInt("bike_id"));
				offer.setBike(b);
				User u = new User();
				UserPostgres newUser = new UserPostgres();
				u = newUser.getById(rs.getInt("buyer_id"));
				offer.setUser(u);
				
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
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from offers where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
				
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();		
			else
				conn.rollback();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Offer add(Offer t) {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offers values (default, ?, ?, ?)";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getBike().getId());
			pstmt.setInt(2, t.getUser().getId());
			pstmt.setString(3, String.valueOf(t.getAmount()));
			
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
	public Set<Offer> getOffersForBike(Bike b) {
		Set<Offer> offers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offers where bike_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("offer_id"));
				offer.setAmount(rs.getDouble("amount"));
				Bike z = new Bike();
				BikePostgres newBike = new BikePostgres();
				z = newBike.getById(rs.getInt("bike_id"));
				offer.setBike(z);
				User u = new User();
				UserPostgres newUser = new UserPostgres();
				u = newUser.getById(rs.getInt("buyer_id"));
				offer.setUser(u);
				
				offers.add(offer);
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
			
			return offers;
	}

	@Override
	public void acceptOffer(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bikes "
					+ "set bike_status=2, owner_id=?, payment_owed=? "
					+ "where bike_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getUser().getId());
			pstmt.setString(2, String.valueOf(t.getAmount()));
			pstmt.setInt(3, t.getBike().getId());
			
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
			
			String sql2 = "delete from offers where bike_id=?";
			
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, t.getBike().getId());
			
			int rowsAffected2 = pstmt2.executeUpdate();
			if(rowsAffected2 > 0)
				conn.commit();
			else
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
