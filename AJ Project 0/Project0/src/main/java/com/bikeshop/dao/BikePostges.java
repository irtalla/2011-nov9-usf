package com.bikeshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Offer;
import com.bikeshop.beans.Person;
import com.bikeshop.utils.ConnectionUtil;

public class BikePostges implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public int addBike(Bike b) {
		int id = 0;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
//			String sql = "insert into bikeshop.bike SET manufacturer=?, model=?, status=?, inventory=?, price=?, tire=?, length=?, description=?";
			String sql = "INSERT INTO bikeshop.bike("
					+ "id,manufacturer, model, status, inventory, price, tire, length, description)"
					+ "VALUES (default, ? , ?, ?, ?, ?, ?, ?, ?);";
					
			String[] keys = {"id"};
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			pst.setString(1, b.getManufacturer());
			pst.setString(2, b.getModel());
			pst.setString(3, b.getStatus());
			pst.setInt(4, b.getInventory());
			pst.setFloat(5, b.getPrice());
			pst.setInt(6, b.getTireSize());
			pst.setInt(7, b.getLength());
			pst.setString(8, b.getDescription());

			
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	


	@Override
	public boolean delBike(Bike bike) {
		boolean deleted = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bikeshop.bike where id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bike.getId());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
				conn.commit();
			} else conn.rollback();
			return deleted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return deleted;
	}

	@Override
	public Bike getByID(int id) {
		Bike b = new Bike();
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.bike where id = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				b.setId(rs.getInt("id"));
				b.setManufacturer(rs.getString("manufacturer"));
				b.setModel(rs.getString("model"));
				b.setStatus(rs.getString("status"));
				b.setInventory(rs.getInt("inventory"));
				b.setPrice(rs.getFloat("price"));
				b.setWeeklyPayment(rs.getFloat("weekly"));
				b.setPaymentsLeft(rs.getInt("payments"));
				b.setTireSize(rs.getInt("tire"));
				b.setLength(rs.getInt("length"));
				b.setDescription(rs.getString("description"));
				b.setOfferNum(rs.getInt("offernum"));
				b.setOwnerID(rs.getInt("ownerID"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return b;
	}
	
	public List<Bike> getByOwner(Integer ownerID) {
		List<Bike> bikes = new ArrayList<>();
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.bike where ownerID = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, ownerID);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Bike b = new Bike();
				b.setId(rs.getInt("id"));
				b.setManufacturer(rs.getString("manufacturer"));
				b.setModel(rs.getString("model"));
				b.setStatus(rs.getString("status"));
				b.setInventory(rs.getInt("inventory"));
				b.setPrice(rs.getFloat("price"));
				b.setWeeklyPayment(rs.getFloat("weekly"));
				b.setPaymentsLeft(rs.getInt("payments"));
				b.setTireSize(rs.getInt("tire"));
				b.setLength(rs.getInt("length"));
				b.setDescription(rs.getString("description"));
				b.setOfferNum(rs.getInt("offernum"));
				b.setOwnerID(rs.getInt("ownerID"));
				
				bikes.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return bikes;
	}


	@Override
	public boolean updateBike(Bike b) {
		boolean updated = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bikeshop.bike set manufacturer=?, model=?, status=?, inventory=?, price=?, weekly=?, payments=?, tire=?, length=?, description=?, offernum=?, ownerid=? where id=?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, b.getManufacturer());
			pst.setString(2, b.getModel());
			pst.setString(3, b.getStatus());
			pst.setInt(4, b.getInventory());
			pst.setFloat(5, b.getPrice());

			pst.setFloat(6, b.getWeeklyPayment());
			pst.setInt(7, b.getPaymentsLeft());
			pst.setInt(8, b.getTireSize());
			pst.setInt(9, b.getLength());
			pst.setString(10, b.getDescription());
			pst.setInt(11, b.getOfferNum());
			if (b.getOwnerID() > 0) {
				pst.setInt(12, b.getOwnerID());
			} else pst.setInt(12, 1);
			pst.setInt(13, b.getId());
			
			pst.executeUpdate();
			
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				updated = true;
				conn.commit();
			} else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public List<Bike> getInventory() {
		
		List<Bike> bikes = new ArrayList<>();
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.bike where status = 'Available'";
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Bike b = new Bike();
				b.setId(rs.getInt("id"));
				b.setManufacturer(rs.getString("manufacturer"));
				b.setModel(rs.getString("model"));
				b.setInventory(rs.getInt("inventory"));
				b.setPrice(rs.getFloat("price"));
				b.setTireSize(rs.getInt("tire"));
				b.setLength(rs.getInt("length"));
				b.setDescription(rs.getString("description"));
				bikes.add(b);
//				System.out.println(b.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	@Override
	public int getPayment() {
		// probably replaced by getbikebyID since there's no situation 
		// where the bike object wouldn't be present
		return 0;
	}
	
	@Override
	public boolean setPayment(Bike b) {
		boolean altered = false;
//		try(Connection conn = cu.getConnection()) {
//			conn.setAutoCommit(false);
//			String sql = "update bike set offernum = ?, weekly = ?, payments = ? where id = ?";
//			
//			
//		} catch (Exception e) {
//			// 
//			e.printStackTrace();
//		}
		
		return altered;
	}

	@Override
	public Set<Offer> viewOffers() {
		// probably going to do an Offer DAO instead
		return null;
	}


}
