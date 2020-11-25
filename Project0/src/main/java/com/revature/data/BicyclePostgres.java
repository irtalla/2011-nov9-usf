package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.BikeType;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BicyclePostgres implements BicycleDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	
	
	@Override
	public Bicycle add(Bicycle t) throws NullPointerException {
		Bicycle b = null;
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.setInt(3, t.getBt().getId());
			pstmt.setDouble(4,  t.getPrice());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//most used method is next
			//if there is a next value...continue with that
			if(rs.next()) {
				b = t;
				b.setId(rs.getInt("id"));
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch(Exception e) {

			e.printStackTrace();
		}
		
		
		return b;
	}

	@Override
	public Bicycle getById(Integer id) {
		Bicycle b = null;
		try(Connection conn = cu.getConnection()){
			
			String sql = "select bs.bike_id, bike_name, bs.status_id, bike_status, biketype_id, bike_type_name, bs.price from \r\n"
					+ "(select bike.id as bike_id, bike.name as bike_name, status.id as status_id, status.status_name as bike_status, biketype_id, bike.price\r\n"
					+ "from bike \r\n"
					+ "join status on \r\n"
					+ "bike.status_id = status.id) \r\n"
					+ "as bs \r\n"
					+ "join biketype on bs.biketype_id = biketype.id\r\n"
					+ "where bs.bike_id =?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  id);
			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new Bicycle();
				b.setId(rs.getInt("bike_id"));
				b.setName(rs.getString("bike_name"));
				BikeType bt = new BikeType();
				bt.setId(rs.getInt("biketype_id"));
				bt.setTypeName(rs.getString("bike_type_name"));
				b.setBt(bt);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("bike_status"));
				b.setStatus(s);
				b.setPrice(rs.getDouble("price"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b; //returns null
	}

	@Override
	public Set<Bicycle> getAll() {
		Set<Bicycle> bikes = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select bs.bike_id, bike_name, bike_status, biketype.bike_type_name as bike_type_name, bs.bike_price from "
					+ "(select bike.id as bike_id, bike.name as bike_name, status.status_name as bike_status, biketype_id, bike.price as bike_price "
					+ "from bike "
					+ "join status on "
					+ "bike.status_id = status.id) "
					+ "as bs "
					+ "join biketype on bs.biketype_id = biketype.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Bicycle bike = new Bicycle();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				BikeType bt = new BikeType();
				bt.setId(rs.getInt("biketype_id"));
				bt.setTypeName(rs.getString("bike_type_name"));
				bike.setBt(bt);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				bike.setPrice(rs.getDouble("bike_price"));
				bikes.add(bike);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	@Override
	public void update(Bicycle t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update bike set name = ?, status_id = ?, biketype_id = ?, price = ? where bike.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.setInt(3,  t.getBt().getId());
			pstmt.setDouble(4, t.getPrice());
			pstmt.setInt(5, t.getId());
			
			int rowsChanged = pstmt.executeUpdate();
			
			if(rowsChanged > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Bicycle t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from person_bike where bike_id = ?;";
					
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			int rowsChanged = pstmt.executeUpdate();
			String sql2 = "delete from bike where id = ?";
			PreparedStatement pstmt2 = conn.prepareCall(sql2);
			pstmt2.setInt(1, t.getId());
			rowsChanged += pstmt2.executeUpdate();
			
			if(rowsChanged > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Set<Bicycle> getAvailableBicycles() {
		Set<Bicycle> bikes = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select bs.bike_id, bike_name, status_id, bike_status, biketype_id, bike_type_name, bs.bike_price from "
					+ "(select  bike.id as bike_id, bike.name as bike_name, status.id as status_id, status.status_name as bike_status, biketype_id, bike.price as bike_price "
					+ "from bike "
					+ "join status on "
					+ "bike.status_id = status.id) "
					+ "as bs "
					+ "join biketype on bs.biketype_id = biketype.id "
					+ "where bs.bike_status = 'available';";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bike_id"));
				b.setName(rs.getString("bike_name"));
				BikeType bt = new BikeType();
				bt.setId(rs.getInt("biketype_id"));
				bt.setTypeName(rs.getString("bike_type_name"));
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("bike_status"));
				b.setStatus(s);
				b.setPrice(rs.getDouble("bike_price"));
				bikes.add(b);
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
		return bikes;
	}
	
}
