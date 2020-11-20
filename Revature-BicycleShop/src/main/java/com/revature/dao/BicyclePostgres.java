package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Employee;
import com.revature.utils.ConnectionUtil;

public class BicyclePostgres implements BicycleDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Set<Bicycle> getAllAvailableBicycles() {
		Set<Bicycle> allTheBicycles = new HashSet<Bicycle>();
		
		
		
		try (Connection conn = cu.getConnection()){			
			String bicycleSelect = "select b.bike_id, b.bike_model, b.bike_type, b.description, "
					+ "b.seller_id, b.price, b.status, b.owner_id, user_username, user_password, "  
					+ "user_id, user_role from bicycles as b join bike_shop_users on "
					+ "(b.seller_id = user_id) where b.status = 'available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(bicycleSelect);
			
			while (rs.next()) {
				int sellerID = rs.getInt("seller_id");
				String username = rs.getString("user_username");
				String password = rs.getString("user_password");
				Employee employee = new Employee(username, password, sellerID);
				
				int bikeID = rs.getInt("bike_id");
				String bikeModel = rs.getString("bike_model");
				String bikeType = rs.getString("bike_type");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				String status = rs.getString("status");
				Bicycle bicycle = new Bicycle(bikeModel, bikeType, description, employee, price, bikeID);
				
				allTheBicycles.add(bicycle);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return allTheBicycles;
	}

	@Override
	public int addABicycle(Bicycle b) {
		int bicycleIndex = -1;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String bicycleAdd = "insert into bicycles values (default, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"bike_id"};
			PreparedStatement pstmt = conn.prepareStatement(bicycleAdd, keys);
			pstmt.setString(1, b.getBikeModel());
			pstmt.setString(2, b.getBikeType());
			pstmt.setString(3, b.getDescription());
			pstmt.setInt(4, b.getSeller().getId());
			pstmt.setDouble(5, b.getPrice());
			pstmt.setString(6, b.getStatus());
			pstmt.setInt(7, (b.getWhoWillOwnTheBike() != null) ? b.getWhoWillOwnTheBike().getId() : -1);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();		
			String nullAddingToCustomer = "update bicycles set owner_id=null where owner_id=-1";
			Statement stmt = conn.createStatement();
			
			if (rs.next()) {
				bicycleIndex = rs.getInt(1);
				stmt.executeUpdate(nullAddingToCustomer);
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return bicycleIndex;
	}

	@Override
	public boolean removeABicycle(Bicycle b) {
		
		Connection conn = cu.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			String removeBicycle = "delete from bicycles where bike_id = ?";
			String removeOffersAboutThatBike = "delete from offers where bike_id = ?;";
			
			PreparedStatement pstmtPrep = conn.prepareStatement(removeOffersAboutThatBike);
			pstmtPrep.setInt(1, b.getId());
			pstmtPrep.executeUpdate();
			
			PreparedStatement pstmt = conn.prepareStatement(removeBicycle);
			pstmt.setInt(1, b.getId());
			pstmt.executeUpdate();
			
			conn.commit();
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
				return false;
			}
		}
		
		return false;
	}

	
	@Override
	public Set<Bicycle> getAllBicycles() {
		Set<Bicycle> allTheBicycles = new HashSet<Bicycle>();
		
		
		
		try (Connection conn = cu.getConnection()){			
			String bicycleSelect = "select b.bike_id, b.bike_model, b.bike_type, b.description, "
					+ "b.seller_id, b.price, b.status, b.owner_id, user_username, user_password, "  
					+ "user_id, user_role from bicycles as b join bike_shop_users on "
					+ "(b.seller_id = user_id)";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(bicycleSelect);
			
			while (rs.next()) {
				int sellerID = rs.getInt("seller_id");
				String username = rs.getString("user_username");
				String password = rs.getString("user_password");
				Employee employee = new Employee(username, password, sellerID);
				
				int bikeID = rs.getInt("bike_id");
				String bikeModel = rs.getString("bike_model");
				String bikeType = rs.getString("bike_type");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				String status = rs.getString("status");
				Bicycle bicycle = new Bicycle(bikeModel, bikeType, description, employee, price, bikeID);
				
				allTheBicycles.add(bicycle);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return allTheBicycles;
	}

}
