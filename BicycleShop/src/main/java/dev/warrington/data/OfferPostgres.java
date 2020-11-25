package dev.warrington.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.warrington.beans.Offer;
import dev.warrington.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	
	@Override
	public Offer add(Offer t) {
		
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "insert into offers values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getBikeId());
			pstmt.setInt(2, t.getCustomerId());
			pstmt.setDouble(3, t.getAmount());
			
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
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Offer o = null;
			
			String sql = "select * from offers";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				o = new Offer(rs.getInt("bike_id"), rs.getInt("customer_id"));
				o.setId(rs.getInt("id"));
				o.setAmount(rs.getDouble("amount"));
				
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
		
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from offers where id = " + t.getId();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAllOffersByBikeId(Integer id) {
		
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from offers where bike_id = " + id;
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
