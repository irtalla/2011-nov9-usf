package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Model;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;



public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Bike add(Bike t) {
		// TODO Auto-generated method stub
		Bike b = null;

		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			//pstmt.setInt(1, t.getId());
			pstmt.setString(1, t.getName());
			pstmt.setInt(4, t.getModel().getId());
			pstmt.setDouble(2, t.getPrice());
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

	@Override
	public Bike getById(Integer id) {
		// TODO Auto-generated method stub
		Bike bike = null;

		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select bike.id as bike_id, bike.name as bike_name, price, status_id, model_id, model.name as model_name, status.id as status_id, status.name as status_name from"
					+ "	bike join status on status_id = status.id join model on model_id = model.id where bike.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setName(rs.getString("bike_name"));
				bike.setPrice(rs.getDouble("price"));
				Model m = new Model();
				m.setId(rs.getInt("model_id"));
				m.setName(rs.getString("model_name"));
				bike.setModel(m);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bike;
	}

	@Override
	public Set<Bike> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from (select bike_status.id, bike_status.name, price, status_id, status_name, model_id,"
					+ "	model.name as model_name from"
					+ "	(select bike.id, bike.name, price, status_id, model_id, status.name as status_name from"
					+ "	bike join status on status_id = status.id) as bike_status"
					+ "	join model on model_id = model.id) as available where available.status_name = 'Available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				bike.setPrice(rs.getDouble("Price"));
				Model m = new Model();
				m.setId(rs.getInt("model_id"));
				m.setName(rs.getString("model_name"));
				bike.setModel(m);
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
	public Set<Bike> getOwnedBikes() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				bike.setPrice(rs.getDouble("Price"));
				Model m = new Model();
				m.setId(rs.getInt("model_id"));
				m.setName(rs.getString("model_name"));
				bike.setModel(m);
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
	public void update(Bike t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			//update from pending to accepted
			String sql = "update bike set status_id = 2 where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			conn.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void updateBike(Bike t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			//update from pending to accepted
			String sql = "update bike set status_id = 2 where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			conn.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Bike b) {
		// TODO Auto-generated method stub
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from bike where bike.id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			//pstmt.setInt(2, b.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
