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
		Bike match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select model, brand, color, status_name from bike join status on bike.status_id = status.id where bike.id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Bike();
				match.setId(rs.getInt("id"));
				match.setBrand(rs.getString("brand"));
				match.setModel(rs.getString("model"));
				match.setBrand(rs.getString("color"));
				
				Status status = this.getStatusById(rs.getInt("status_id"));
				match.setStatus(status);
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return match;
	}

	private Status getStatusById(Integer id) {
		Status match = null;
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select name from status where id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Status();
				match.setId(id);
				match.setName(rs.getString("name"));
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select status.id, status.name, brand, model, color, status_id "
					+ "from bike join status on status_id = status.id ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("brand"));
				bike.setModel(rs.getString("model"));
				bike.setModel(rs.getString("color"));
				
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setName(rs.getString("status_name"));
				bike.setStatus(status);
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> allAvailable = new HashSet<>();
		for(Bike bike : this.getAll()) {
			if(bike.getStatus().getName().equals("Available")) {
				allAvailable.add(bike);
			}
		}
		return allAvailable;
	}

	@Override
	public Bike update(Bike t) {
		Bike match = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "update bike set brand = ?, model = ?, color = ?, status_id = ? where id = ?";
			String[] keys = {"id", "brand", "model", "color", "status_id", "id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getBrand());
			pstmt.setString(2, t.getModel());
			pstmt.setString(3, t.getColor());
			pstmt.setInt(4, t.getStatus().getId());
			pstmt.setInt(5, t.getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("brand"));
				bike.setModel(rs.getString("model"));
				bike.setModel(rs.getString("color"));
				
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setName(rs.getString("status_name"));
				bike.setStatus(status);
				match = bike;
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}

	@Override
	public void delete(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bike where id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
