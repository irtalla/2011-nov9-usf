package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Bike add(Bike t) {
	Bike b = null;
	
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);
		String sql = "insert into bike values (default, ?, ?, ?, ?)";
		String[] keys = {"id"};
		PreparedStatement pstmt = conn.prepareStatement(sql, keys);
		pstmt.setString(1, t.getModel());
		pstmt.setString(2, t.getColor());
		pstmt.setFloat(3, t.getPrice());
		pstmt.setInt(4, t.getStatus().getId());
		

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

	@Override
	public Bike getById(Integer id) {
		Bike b = null;
		Bike bike = new Bike();
		
		try (Connection conn = cu.getConnection()) {
		
			String sql = "select * from bike where id =?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
		   

			pstmt.executeQuery();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSet set = pstmt.executeQuery();
            
			while (set.next()) {

			    bike.setId(set.getInt(1));
				bike.setModel(set.getString(2));
				bike.setColor(set.getString(3));
				bike.setPrice(set.getFloat(4));
				Status s = new Status();
				s.setId(set.getInt(5));
	
				bike.setStatus(s);
	
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
	}
		return bike;
	}
	

	@Override
	public Set<Bike> getByOwnerId(Integer id) {
		Bike b = null;
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
		
			String sql = "select * from bike where owner_id =?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
		   

			pstmt.executeQuery();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSet set = pstmt.executeQuery();
            
			while (set.next()) {
				Bike bike = new Bike();
			    bike.setId(set.getInt(1));
				bike.setModel(set.getString(2));
				bike.setColor(set.getString(3));
				bike.setPrice(set.getFloat(4));
				Status s = new Status();
				s.setId(set.getInt(5));
	
				bike.setStatus(s);
				bikes.add(bike);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
	}
		return bikes;
	}
	

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {

			String sql = "SELECT * from bike";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				bike.setColor(rs.getString("color"));
				bike.setPrice(rs.getFloat("price"));
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				if (s.getId()==2) s.setName("Purchased");
				else s.setName("Available");

				bike.setStatus(s);
				bike.setOwnerId(rs.getInt("owner_id"));
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	@Override
	public void update(Bike t) {
		Bike b = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set model = ?, color = ?, price = ?, status_id =?, owner_id=? where id =?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getModel());
			pstmt.setString(2, t.getColor());
			pstmt.setFloat(3, t.getPrice());
			pstmt.setInt(4, t.getStatus().getId());
			pstmt.setInt(5, t.getOwnerId());
			pstmt.setInt(6, t.getId());
			
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
		
	}
	
	@Override
	public void edit(Bike t) {
		Bike b = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set model = ?, color = ?, price = ?, status_id =? where id =?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getModel());
			pstmt.setString(2, t.getColor());
			pstmt.setFloat(3, t.getPrice());
			pstmt.setInt(4, t.getStatus().getId());
			pstmt.setInt(5, t.getId());
			
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
		
	}

	@Override
	public void delete(Bike t) {
		Offer o = null;
		Bike bike = new Bike();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from bike where id =?";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
		   

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
	}
	}
	
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {

			String sql = "SELECT * from bike where status_id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				bike.setColor(rs.getString("color"));
				bike.setPrice(rs.getFloat("price"));
				Status s = new Status();
				s.setId(rs.getInt("status_id")); 
			
				bike.setStatus(s);
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	
	
	}
	@Override
	public Set<Bike> getBikesByPersonId(Integer id){
		Set<Bike> bikes = new HashSet<>();
		BikeDAO bikeDao = new BikePostgres();
		
		
		try (Connection conn = cu.getConnection()) {
		
		
		String sql = "select * from bike where owner_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			 Bike b = bikeDao.getById(rs.getInt("id"));  /// change this from bike_id
			bikes.add(b);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bikes;
	}
}
