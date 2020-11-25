package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.beans.Type;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	public Bike getById(Integer id) {
		Bike bike = null;

		try (Connection conn = cu.getConnection()) {			

			String sql = "select bike_status.id, bike_status.name, year, status_id, status_name, brand_id, color, price, type_id, type_name, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, year, status_id, brand_id, status.name as status_name from "
					+ "bike join status on status_id = status.id) as bike_status "
					+ "join brand on brand_id = brand.id where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setColor(rs.getString("color"));
				bike.setYear(rs.getInt("year"));
				bike.setPrice(rs.getDouble("price"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				Type t = new Type();
				t.setId(rs.getInt("type_id"));
				t.setName(rs.getString("type_name"));
				bike.setStatus(s);
				bike.setOffers(getOffersByID(bike.getId(), conn));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	private Set<Offer> getOffersByID(Integer id, Connection conn) {
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn1 = cu.getConnection()) {			
			String sql = "select * from( bike_status.id, bike_status.name, year, status_id, status_name, brand_id, color, price, type_id, type_name, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, year, status_id, brand_id, status.name as status_name from "
					+ "bike join status on status_id = status.id) as bike_status "
					+ "join brand on brand_id = brand.id as available where available.status_name = 'Available'";
			PreparedStatement pstmt = conn1.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setColor(rs.getString("color"));
				bike.setYear(rs.getInt("year"));
				bike.setPrice(rs.getDouble("price"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				Type t = new Type();
				t.setId(rs.getInt("type_id"));
				t.setName(rs.getString("type_name"));
				bike.setStatus(s);
				bike.setOffers(getOffersByID(bike.getId(), conn1));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	
	}

	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_status.id, bike_status.name, year, status_id, status_name, brand_id, color, price, type_id, type_name, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, year, status_id, brand_id, status.name as status_name from "
					+ "bike join status on status_id = status.id) as bike_status "
					+ "join brand on brand_id = brand.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setColor(rs.getString("color"));
				bike.setYear(rs.getInt("year"));
				bike.setPrice(rs.getDouble("price"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				Type t = new Type();
				t.setId(rs.getInt("type_id"));
				t.setName(rs.getString("type_name"));
				bike.setStatus(s);
				
				bike.setOffers(getOffersByID(bike.getId(), conn));
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	public void update(Bike t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Bike t) {
		// TODO Auto-generated method stub
		
	}

	public Bike add(Bike b) {
		Bike t = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?, ?, ?, ?) ";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, b.getYear());
			pstmt.setDouble(2, b.getPrice());
			pstmt.setString(3, b.getColor());
			pstmt.setInt(4, b.getType().getId());
			pstmt.setInt(5, b.getBrand().getId());
			pstmt.setInt(6, b.getStatus().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				if (t.getOffers().size() > 0) {
					if (addOffers(t, conn)) {
						b = t;
						b.setId(rs.getInt(1));
						conn.commit();
					} else {
						conn.rollback();
					}
				} else {
					b = t;
					b.setId(rs.getInt(1));
					conn.commit();
				}
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	private boolean addOffers(Bike t, Connection conn) throws SQLException {
		String sql = "insert into bike_offers values";
		for (int i = 0; i < t.getOffers().size(); i++) {
			sql += " (?,?)";
			if (i < t.getOffers().size() - 1)
				sql += ",";
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 0;
		for (Offer of : t.getOffers()) {
			pstmt.setInt(++i, t.getId());
			pstmt.setDouble(++i, of.getOffer());
		}
		int rowsAffected = pstmt.executeUpdate();
		
		return (rowsAffected == t.getOffers().size());
	}

	public Set<Bike> getAvailableBikes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getOffer() {
		// TODO Auto-generated method stub
		return null;
	}

}
