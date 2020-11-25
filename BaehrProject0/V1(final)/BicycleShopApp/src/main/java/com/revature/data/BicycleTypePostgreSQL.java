package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.util.ConnectionUtil;


public class BicycleTypePostgreSQL implements BicycleTypeDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Integer add(BicycleType bt) {
		if(bt == null) {
			System.out.println("Cannot add a null bicycle type to the database");
			return null;
		}
		else {
		BicycleType n = new BicycleType();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bicycle_types values(default, ?, ?)";
			String[] keys = {"type_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  bt.getManufacturer());
			pstmt.setString(2,  bt.getModel());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				n=bt;
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
			return n.getId();
		}
	}
	
	@Override
	public Set<BicycleType> getAll() {
		Set<BicycleType> bikeTypes = new HashSet<BicycleType>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycle_types ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(1));
					bt.setManufacturer(rs.getString(2));
					bt.setModel(rs.getString(3));
					bikeTypes.add(bt);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return bikeTypes;
	}

	@Override
	public BicycleType getById(Integer id) {
		BicycleType bt = null;
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select * from bicycle_types "
						+ " where type_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				if(rs.next()) {
					BicycleType n = new BicycleType();
					n.setId(rs.getInt(1));
					n.setManufacturer(rs.getString(2));
					n.setModel(rs.getString(3));
					bt = n;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return bt;
	}

}
