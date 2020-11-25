package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.util.ConnectionUtil;

public class BicyclePostgreSQL implements BicycleDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bicycle add(Bicycle b) {
		if(b == null) {
			System.out.println("Cannot add a null user to the database");
			return b;
		}
		else {
		Bicycle n = new Bicycle();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bicycles values(default, ?, ?, ?, ?, ?)";
			String[] keys = {"bicycle_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setInt(1,  b.getBicycleType().getId());
			pstmt.setInt(2,  b.getYear());
			pstmt.setDouble(3,  b.getPrice());
			pstmt.setInt(4,  b.getOwner_id());
			pstmt.setString(5,  b.getStatus());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				n=b;
				n.setId(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			return n;
		}
	}

	@Override
	public Set<Bicycle> getAll() {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycles "
						+ " join bicycle_types "
						+ " on bicycles.type_id = bicycle_types.type_id "
						+ " order by bicycle_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(2));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					
					b.setId(rs.getInt(1));
					b.setYear(rs.getInt(3));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(5));
					b.setStatus(rs.getString(6));
					bikes.add(b);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return bikes;
	}
	
	@Override
	public Set<Bicycle> getAvailable() {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycles "
						+ " join bicycle_types "
						+ " on bicycles.type_id = bicycle_types.type_id "
						+ " where bicycles.owner_id = 1 "
						+ " order by bicycle_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(2));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					
					b.setId(rs.getInt(1));
					b.setYear(rs.getInt(3));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(5));
					b.setStatus(rs.getString(6));
					bikes.add(b);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return bikes;
	}
	
	@Override
	public Set<Bicycle> getByUserId(Integer id) {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycles "
						+ " join bicycle_types "
						+ " on bicycles.type_id = bicycle_types.type_id "
						+ " where bicycles.owner_id = ? "
						+ " order by bicycle_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(2));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					
					b.setId(rs.getInt(1));
					b.setYear(rs.getInt(3));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(5));
					b.setStatus(rs.getString(6));
					bikes.add(b);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return bikes;
	}

	@Override
	public Bicycle getById(Integer id) {
		Bicycle bike = null;
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycles "
						+ " join bicycle_types "
						+ " on bicycles.type_id = bicycle_types.type_id "
						+ " where bicycles.bicycle_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				if(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(2));
					bt.setManufacturer(rs.getString(8));
					bt.setModel(rs.getString(9));
					b.setBicycleType(bt);
					
					b.setId(rs.getInt(1));
					b.setYear(rs.getInt(3));
					b.setPrice(rs.getDouble(4));
					b.setOwner_id(rs.getInt(5));
					b.setStatus(rs.getString(6));
					bike = b;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return bike;
	}

	@Override
	public void update(Bicycle t) {
		if(t == null) {
			System.out.println("Cannot update a null bicycle to the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update bicycles "
						+" set type_id = ?, "
						+" year = ?, "
						+" price = ?, "
						+" owner_id = ?, "
						+" status = ?"
						+" where bicycle_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getBicycleType().getId());
				pstmt.setInt(2, t.getYear());
				pstmt.setDouble(3, t.getPrice());
				pstmt.setInt(4, t.getOwner_id());
				pstmt.setString(5, t.getStatus());
				pstmt.setInt(6, t.getId());
				
				int r = pstmt.executeUpdate();
				//System.out.println(r);
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
	public void delete(Bicycle t) {
		if(t == null) {
			System.out.println("Cannot delete a null bicycle from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				//first delete any outstanding offers for the bicycle
				String sql = "delete from offers where bicycle_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.executeUpdate();
				// then delete any purchase records for the bike
				String sql2 = "update purchases "
						+" set bicycle_id = null"
						+" where bicycle_id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, t.getId());
				pstmt2.executeUpdate();
				// finally delete bicycle from bicycles table
				String sql3 = "delete from bicycles "
						+" where bicycle_id = ?";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, t.getId());
				int r = pstmt3.executeUpdate();
				
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
}
