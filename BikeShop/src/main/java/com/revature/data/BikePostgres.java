package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Bike getById(Integer id) {
		Bike bike = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_overall.bike_id, color, bike_status, status_name, payment_owed, bike_brand, "
					+ "brands.name as brand_name from "
					+ "(select bikes.bike_id, color, bike_status, payment_owed, bike_brand, statuses.status_name as status_name from "
					+ "bikes join statuses on bike_status = statuses.status_id) as bike_overall "
					+ "join brands on bike_brand = brands.brand_id where bike_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				Brand b = new Brand();
				b.setId(rs.getInt("bike_brand"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("bike_status"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				if (rs.getString("payment_owed") != null){
					String paymentTemp = rs.getString("payment_owed");
					bike.setPayment(Double.valueOf(paymentTemp));
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bike;
	}

	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_overall.bike_id, color, status_id, status_name, payment_owed, brand_id, "
					+ "brands.name as brand_name from "
					+ "(select bikes.bike_id, color, bike_status, payment_owed, bike_brand, statuses.status_name as status_name from "
					+ "bikes join statuses on bike_status = statuses.status_id) as bike_overall "
					+ "join brands on bike_brand = brands.brand_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				if (rs.getString("payment_owed") != null){
					String paymentTemp = rs.getString("payment_owed");
					bike.setPayment(Double.valueOf(paymentTemp));
				}
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	public void update(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "";
			PreparedStatement pstmt = null;
			if (t.getOwner() != null) {
				sql = "update bikes set color = ?, bike_brand = ?, bike_status = ?, owner_id = ?, payment_owed = ? where bike_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getColor());
				pstmt.setInt(2, t.getBrand().getId());
				pstmt.setInt(3, t.getStatus().getId());
				pstmt.setInt(4, t.getOwner().getId());
				pstmt.setString(5, String.valueOf(t.getPayment()));
				pstmt.setInt(6, t.getId());
			} else {
				sql = "update bikes set color = ?, bike_brand = ?, bike_status = ?, owner_id = null, payment_owed = ? where bike_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getColor());
				pstmt.setInt(2, t.getBrand().getId());
				pstmt.setInt(3, t.getStatus().getId());
				pstmt.setString(4, String.valueOf(t.getPayment()));
				pstmt.setInt(5, t.getId());	
			}
			
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();		
			else
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delete(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bikes where bike_id = ?";
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

	public Bike add(Bike t) {
		Bike b = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bikes values (default, ?, ?, ?, null, null)";
			String[] keys = {"bike_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getColor());
			pstmt.setInt(2, t.getBrand().getId());
			pstmt.setInt(3, t.getStatus().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				b = t;
				b.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return b;
	}

	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from (select bike_status.bike_id, color, status_id, status_name, payment_owed, bike_brand, " 
					+ "brands.name as brand_name from "
					+ "(select bikes.bike_id, bikes.color, bike_brand, status_id, payment_owed, statuses.status_name as status_name from " 
					+ "bikes join statuses on bike_status = statuses.status_id) as bike_status "
					+ "join brands on bike_brand = brands.brand_id) as available where available.status_name = 'Available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				Brand b = new Brand();
				b.setId(rs.getInt("bike_brand"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				if (rs.getString("payment_owed") != null){
					String paymentTemp = rs.getString("payment_owed");
					bike.setPayment(Double.valueOf(paymentTemp));
				}
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

}
