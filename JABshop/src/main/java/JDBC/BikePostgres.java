package JDBC;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;

import Entity.Bike;
import Entity.Brand;
import Entity.Status;
import Utils.ConnectionUtil;

public class BikePostgres implements BikeJDBC{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bike add(Bike b) {
		Bike t = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getName());
			pstmt.setInt(2, b.getBrand().getId());
			pstmt.setInt(3, b.getStatus().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
					t = b;
					t.setId(rs.getInt(1));
					conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;

	}

	@Override
	public Bike getById(Integer id) {
		Bike bike = null;

		try (Connection conn = cu.getConnection()) {			
			String sql = "select bike.id as id,bike.names as names,bike.brand_id as brand_id,brand.names as brand_name,bike.status_id \r\n" + 
					"as status_id,status.names as status_name from bike \r\n" + 
					"join status on bike.status_id = status.id \r\n" + 
					"join brand on bike.brand_id =brand.id where bike.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("names"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setBname(rs.getString("brand_name"));
				bike.setBrand(b);
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
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_status.id, bike_status.name, status_id, status_name, brand_id, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, status_id, brand_id, status.name as status_name from "
					+ "bike join status on status_id = status.id) as bike_status "
					+ "join brand on brand_id = brand.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setBname(rs.getString("brand_name"));
				bike.setBrand(b);
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
	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike.id as id,bike.names as names,bike.brand_id as brand_id,brand.names as brand_name,bike.status_id \r\n" + 
					"as status_id,status.names as status_name from bike \r\n" + 
					"join status on bike.status_id = status.id \r\n" + 
					"join brand on bike.brand_id =brand.id where bike.status_id =1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("names"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setBname(rs.getString("brand_name"));
				bike.setBrand(b);
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
	public void update(Bike b) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set names = ?, status_id = ?, brand_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getName());
			pstmt.setInt(2, b.getStatus().getId());
			pstmt.setInt(3, b.getBrand().getId());
			pstmt.setInt(4, b.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

	@Override
	public void remove(Bike b) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql;
			PreparedStatement pstmt;

				sql = "delete from bike where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b.getId());
				int rowsAffected = pstmt.executeUpdate();
				rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0)
					conn.commit();		
				else
					conn.rollback();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
