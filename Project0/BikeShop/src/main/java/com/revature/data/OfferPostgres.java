package com.revature.data;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer o) {
		Offer ofr = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (?)";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, o.getOfferStatus());
	
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				if (o.getOfferStatus().equals("pending")) {
						ofr = o;
						ofr.setId(rs.getInt(1));
						conn.commit();
				} else {
						conn.rollback();
			
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return ofr;
	}

	@Override
	public Offer getById(Integer id) {
		Offer ofr = null;

		try (Connection conn = cu.getConnection()) {			
			
			String sql = "";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
		
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ofr;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "";
		/* add casts to fix errors to the following statements
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		*/
			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			
			while (rs.next()) {
	
										
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Offer t) {
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Offer> getAvailableOffers() {
		// TODO Auto-generated method stub
		return null;
	}

}
