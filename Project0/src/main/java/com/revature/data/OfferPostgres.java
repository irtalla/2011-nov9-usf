package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.beans.Bike;
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
		try(Connection conn = cu.getConnection()) {
			if(id != null) {
				conn.setAutoCommit(false);
				String sql = "select * from Offer where Offer_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					o = new Offer();
					o.setId(rs.getInt("id"));
					o.setUserId(rs.getInt("userId"));
					o.setUserId(rs.getInt("bikeId"));
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Offer> getAvailableOffers() {
		return null;
	}
	
	@Override
	public void update(Offer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub

	}
}
