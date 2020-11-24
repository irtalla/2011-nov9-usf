package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bike add(Bike t) {
		Bike c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getBrand());
			pstmt.setString(2, t.getModel());
			pstmt.setString(3, t.getColor());
			pstmt.setInt(4, t.getStatus().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
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
	public Bike getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select status.id, status.name, brand, model, color, status_id, status_name, "
					+ "from bike join status on status_id = status.id ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("brand"));
				bike.setModel(rs.getString("model"));
				bike.setModel(rs.getString("color"));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		return null;
	}

	@Override
	public Bike update(Bike t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Bike t) {
		// TODO Auto-generated method stub

	}

}
