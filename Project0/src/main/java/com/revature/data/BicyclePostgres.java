package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import com.revature.utils.ConnectionUtil;

import com.revature.beans.Bicycle;
import com.revature.beans.Color;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Person;
import com.revature.beans.Status;

public class BicyclePostgres implements BicycleDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private PersonPostgres personDAO = new PersonPostgres();

	@Override
	public Bicycle add(Bicycle t){
		Bicycle b = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"bicycle_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getModel());
			pstmt.setInt(2, t.getColor().getId());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setDouble(4, t.getPrice());
			pstmt.setInt(5, t.getOwner().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				b = t;
				b.setId(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public Bicycle getById(Integer id) {
		Bicycle b = null;
		
		try (Connection conn = cu.getConnection()){
			String sql = "select bicycle.bicycle_id, " + 
					"bicycle.bicycle_model, " + 
					"bicycle.price, " + 
					"bicycle.status_id, " + 
					"status.status_name, " + 
					"bicycle.color_id, " + 
					"color.color_name, " + 
					"bicycle.owner_id " + 
					"from bicycle " + 
					"join status " + 
					"on bicycle.status_id = status.status_id " + 
					"join color " + 
					"on bicycle.color_id = color.color_id " + 
					"where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("bicycle_model"));
				b.setPrice(rs.getDouble("price"));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				b.setStatus(s);
				
				Color c = new Color();
				c.setId(rs.getInt("color_id"));
				c.setName(rs.getString("color_name"));
				b.setColor(c);
				
				b.setOwner(personDAO.getById(rs.getInt("owner_id")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public Set<Bicycle> getAll() {
		Set<Bicycle> bicycles = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select bicycle.bicycle_id, " + 
					"bicycle.bicycle_model, " + 
					"bicycle.price, " + 
					"bicycle.status_id, " + 
					"status.status_name, " + 
					"bicycle.color_id, " + 
					"color.color_name, " + 
					"bicycle.owner_id " + 
					"from bicycle " + 
					"join status " + 
					"on bicycle.status_id = status.status_id " + 
					"join color " + 
					"on bicycle.color_id = color.color_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("bicycle_model"));
				b.setPrice(rs.getDouble("price"));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				b.setStatus(s);
				
				Color c = new Color();
				c.setId(rs.getInt("color_id"));
				c.setName(rs.getString("color_name"));
				b.setColor(c);
				
				b.setOwner(personDAO.getById(rs.getInt("owner_id")));
				bicycles.add(b);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return bicycles;
	}

	@Override
	public void update(Bicycle t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update bicycle set "
					+ "bicycle_model = ?, "
					+ "price = ?, "
					+ "status_id = ?, "
					+ "color_id = ?, "
					+ "owner_id = ? "
					+ "where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getModel());
			pstmt.setDouble(2, t.getPrice());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getColor().getId());
			pstmt.setInt(5, t.getOwner().getId());
			pstmt.setInt(6, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bicycle t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			// We need to delete the offers for the bicycle first
			String sql = "delete from offer where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			
			// Now we delete the bicycle
			sql = "delete from bicycle where bicycle_id = ?";
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0)
				conn.commit();
			else {
				conn.rollback();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Set<Bicycle> getAvailableBicycles() {
		Set<Bicycle> availableBicycles = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select bicycle.bicycle_id, " + 
					"bicycle.bicycle_model, " + 
					"bicycle.price, " + 
					"bicycle.status_id, " + 
					"status.status_name, " + 
					"bicycle.color_id, " + 
					"color.color_name, " + 
					"bicycle.owner_id " + 
					"from bicycle " + 
					"join status " + 
					"on bicycle.status_id = status.status_id " + 
					"join color " + 
					"on bicycle.color_id = color.color_id " + 
					"where bicycle.status_id = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("bicycle_model"));
				b.setPrice(rs.getDouble("price"));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				b.setStatus(s);
				
				Color c = new Color();
				c.setId(rs.getInt("color_id"));
				c.setName(rs.getString("color_name"));
				b.setColor(c);
				
				b.setOwner(personDAO.getById(rs.getInt("owner_id")));
				
				availableBicycles.add(b);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return availableBicycles;
	}
	
	@Override
	public Set<Bicycle> getBicyclesByOwner(Person p) {
		Set<Bicycle> ownedBicycles = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select bicycle.bicycle_id, " + 
					"bicycle.bicycle_model, " + 
					"bicycle.price, " + 
					"bicycle.status_id, " + 
					"status.status_name, " + 
					"bicycle.color_id, " + 
					"color.color_name, " + 
					"bicycle.owner_id " + 
					"from bicycle " + 
					"join status " + 
					"on bicycle.status_id = status.status_id " + 
					"join color " + 
					"on bicycle.color_id = color.color_id " +
					"where bicycle.owner_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("bicycle_model"));
				b.setPrice(rs.getDouble("price"));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				b.setStatus(s);
				
				Color c = new Color();
				c.setId(rs.getInt("color_id"));
				c.setName(rs.getString("color_name"));
				b.setColor(c);
				
				b.setOwner(personDAO.getById(rs.getInt("owner_id")));
				
				ownedBicycles.add(b);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ownedBicycles;
	}

}
