package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.Purchase;
import com.revature.util.ConnectionUtil;

public class PurchasePostgreSQL implements PurchaseDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Set<Purchase> getByUserId(Integer id) { //gets all purchases logged for a given user ID
		Set<Purchase> purchases = new HashSet<Purchase>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select p.purchase_id, p.bicycle_id, p.user_id, p.price,  "
						+ "  b.status, b.year, b.type_id, bt.manufacturer, bt.model " 
						+ " from purchases p" 
						+ " join bicycles b on p.bicycle_id = b.bicycle_id" 
						+ " join bicycle_types bt on b.type_id = bt.type_id "
						+ " where p.user_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Purchase p = new Purchase();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(7));
					bt.setManufacturer(rs.getString(9));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					b.setId(rs.getInt(2));
					b.setYear(rs.getInt(6));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(3));
					b.setStatus(rs.getString(5));
					p.setBicycle(b);
					p.setPurchase_id(rs.getInt(1));
					p.setUser_id(rs.getInt(3));
					p.setPrice(rs.getDouble(4));
					purchases.add(p);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return purchases;
	}

	@Override
	public void update(Purchase p) {
		if(p == null) {
			System.out.println("Cannot update a null offer to the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update purchases "
						+ " set user_id = ?, "
						+ " price = ? "
						+ " where purchase_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p.getUser_id());
				pstmt.setDouble(2, p.getPrice());
				pstmt.setInt(3, p.getPurchase_id());
				
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
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
		
	}

	@Override
	public void delete(Purchase p) {
		if(p == null) {
			System.out.println("Cannot delete a null purchase from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from purchases where purchase_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p.getPurchase_id());
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
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
	}

	@Override
	public Purchase add(Purchase p) {
		if(p == null) {
			System.out.println("Cannot add a null user to the database");
		}
		else {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into purchases values(default, ?, ?, ?)";
			String[] keys = {"purchase_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setInt(1,  p.getBicycle().getId());
			pstmt.setInt(2,  p.getUser_id());
			pstmt.setDouble(3,  p.getPrice());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			String sql3 = "select account_balance "
					+ " from users "
					+ " where user_id = ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1,  p.getUser_id());
			
			ResultSet rs3 = pstmt3.executeQuery();
			double currentBalance = 0.0;
			if(rs3.next()) {
				currentBalance = rs3.getDouble(1);
			}
			
			String sql2 = "update users "
					+ " set account_balance =  ?)"
					+ "where user_id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setDouble(1,  (currentBalance + p.getPrice()));
			pstmt.setInt(2,  p.getUser_id());
			
			if(rs.next()) {
				p.setPurchase_id(rs.getInt(1));
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
		return p;
	}

	@Override
	public Set<Purchase> getAll() {
		Set<Purchase> purchases = new HashSet<Purchase>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select p.purchase_id, p.bicycle_id, p.user_id, p.price,  "
						+ " b.status, b.year, b.type_id, bt.manufacturer, bt.model " 
						+ " from purchases p" 
						+ " join bicycles b on p.bicycle_id = b.bicycle_id" 
						+ " join bicycle_types bt on b.type_id = bt.type_id ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Purchase p = new Purchase();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(7));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					b.setId(rs.getInt(2));
					b.setYear(rs.getInt(6));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(3));
					b.setStatus(rs.getString(5));
					p.setBicycle(b);
					p.setPurchase_id(rs.getInt(1));
					p.setUser_id(rs.getInt(3));
					p.setPrice(rs.getDouble(4));
					purchases.add(p);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return purchases;
	}

	@Override
	public Purchase getById(Integer id) {
		Purchase p = null;
		try (Connection conn = cu.getConnection()){
				Purchase n = new Purchase();
				conn.setAutoCommit(false);
				String sql = "select p.purchase_id, p.bicycle_id, p.user_id, p.price, "
						+ "  b.status, b.year, b.type_id, bt.manufacturer, bt.model " 
						+ " from purchases p" 
						+ " join bicycles b on p.bicycle_id = b.bicycle_id" 
						+ " join bicycle_types bt on b.type_id = bt.type_id "
						+ " where p.purchase_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				if(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(7));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					b.setId(rs.getInt(2));
					b.setYear(rs.getInt(6));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(3));
					b.setStatus(rs.getString(5));
					n.setBicycle(b);
					n.setPurchase_id(rs.getInt(1));
					n.setUser_id(rs.getInt(3));
					n.setPrice(rs.getDouble(4));
					p = n;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return p;
	}

}
