package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.BikeType;
import com.revature.utils.ConnectionUtil;

public class BikeTypePostgres implements BikeTypeDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public BikeType add(BikeType bt) {
		BikeType bType = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into biketype values (default, ?);";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, bt.getTypeName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				bType=bt;
				bType.setId(rs.getInt("id"));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bt;
	}
	
	
	@Override
	public BikeType getById(Integer id) throws NullPointerException {
		BikeType bt = null;
		try(Connection conn = cu.getConnection()) {
			if(id!=null) {
				String sql = "select * from biketype where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				ResultSet rs = pstmt.executeQuery(sql);
				if(rs.next()) {
					bt = new BikeType();
					bt.setId(rs.getInt("id"));
					bt.setTypeName(rs.getString("bike_type_name"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bt;
	}
	
	public BikeType getBikeTypeByName(String name) throws NullPointerException{
		BikeType bt = null;
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			if(name != null) {
				String sql = "select * from biketype where bike_type_name = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery(sql);
				if(rs.next()) {
					bt = new BikeType();
					bt.setId(rs.getInt("id"));
					bt.setTypeName(rs.getString("bike_type_name"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bt;
	}

	@Override
	public Set<BikeType> getAll() {
		Set<BikeType> types = new HashSet<>();
		
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from biketype;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BikeType bt = new BikeType();
				bt.setId(rs.getInt("id"));
				bt.setTypeName(rs.getString("bike_type_name"));
				types.add(bt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	public void update(BikeType t) {
		try(Connection conn = cu.getConnection()){
			if(!t.equals(null)) {
				conn.setAutoCommit(false);
				String sql = "update biketype set name = ? where id = ?;";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getTypeName());
				pstmt.setInt(2, t.getId());
				
				int rowsChanged = pstmt.executeUpdate();
				if(rowsChanged > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BikeType t) {
		try(Connection conn = cu.getConnection()){
			if(t != null) {
				conn.setAutoCommit(false);
				String sql = "delete from biketype where id = ?;";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  t.getId());
				
				int rowsChanged = pstmt.executeUpdate();
				
				if(rowsChanged > 0){
					conn.commit();
				}else {
					conn.rollback();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}



}
