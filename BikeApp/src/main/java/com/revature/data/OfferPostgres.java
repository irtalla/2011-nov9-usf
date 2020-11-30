package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()) {
			Offer o = null;
			
			String sql = "select * from offers";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				o = new Offer();
				o.setId(rs.getInt("id"));
				o.setName(rs.getString("name"));
				o.setOffer(rs.getDouble("offer"));
				
				offers.add(o);
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
		
		try (Connection conn = cu.getConnection()){
			String sql = "delete from offers where id = " + t.getName();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Offer add(Offer o) {
		Offer f = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offers values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, o.getId());
			pstmt.setString(2, o.getName());
			pstmt.setDouble(3, o.getOffer());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()){
				f = o;
				f.setId(rs.getInt(1));
				conn.commit();
			} else conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public Set<Offer> getOffers() {
		// TODO Auto-generated method stub
		return null;
	}

}
