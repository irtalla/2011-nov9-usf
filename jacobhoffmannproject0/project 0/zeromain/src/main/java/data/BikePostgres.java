package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import models.Bike;
import models.Model;
import models.Status;
import utils.ConnectionUtil;

public class BikePostgres implements BikeDao {
	//private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Bike add(Bike t) {
		Bike c = t;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?, ?)";
			//String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getColor());
			pstmt.setString(2, t.getSize());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getModeltype().getId());
			
			pstmt.executeUpdate();
			
					conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
			}
	public Set<Bike> getAvailableBikes(){
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_status.status_id, bike_id, color, bike_size, bike_status.model_id, bike_status.status_id, status_avalibilty,  "
					+ "model.mname as model_name, btype, year_made, brand from "
					+ "(select bike.bike_id, bike.color, bike.bike_size, bike.status_id, bike.model_id, status.avalibilty as status_avalibilty from "
					+ " bike join status on status.status_id = bike.status_id) as bike_status "
					+ "join model on bike_status.model_id = model.model_id where status_id = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				bike.setSize(rs.getString("bike_size"));
				Model b = new Model();
				b.setId(rs.getInt("model_id"));
				b.setName(rs.getString("model_name"));
				b.setType(rs.getString("btype"));
				b.setYear(rs.getInt("year_made"));
				b.setBrand(rs.getString("brand"));
				bike.setModeltype(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setAvalibilty(rs.getString("status_avalibilty"));
				bike.setStatus(s);
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
		
	}
	@Override
	public Bike getById(Integer id) {
		Bike bike = null;

		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select bike_status.status_id, bike_id, color, bike_size, bike_status.model_id, bike_status.status_id, status_avalibilty,  "
					+ "model.mname as model_name, btype, year_made, brand from "
					+ "(select bike.bike_id, bike.color, bike.bike_size, bike.status_id, bike.model_id, status.avalibilty as status_avalibilty from "
					+ " bike join status on status.status_id = bike.status_id) as bike_status "
					+ "join model on bike_status.model_id = model.model_id where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				bike.setSize(rs.getString("bike_size"));
				Model b = new Model();
				b.setId(rs.getInt("model_id"));
				b.setName(rs.getString("model_name"));
				b.setType(rs.getString("btype"));
				b.setYear(rs.getInt("year_made"));
				b.setBrand(rs.getString("brand"));
				bike.setModeltype(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setAvalibilty(rs.getString("status_avalibilty"));
				bike.setStatus(s);
			}
		}catch(Exception e) {
			e.printStackTrace();

		}
		return bike;
	}
	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select bike_status.status_id, bike_id, color, bike_size, bike_status.model_id, bike_status.status_id, status_avalibilty,  "
					+ "model.mname as model_name, btype, year_made, brand from "
					+ "(select bike.bike_id, bike.color, bike.bike_size, bike.status_id, bike.model_id, status.avalibilty as status_avalibilty from "
					+ " bike join status on status.status_id = bike.status_id) as bike_status "
					+ "join model on bike_status.model_id = model.model_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setColor(rs.getString("color"));
				bike.setSize(rs.getString("bike_size"));
				Model b = new Model();
				b.setId(rs.getInt("model_id"));
				b.setName(rs.getString("model_name"));
				b.setType(rs.getString("btype"));
				b.setYear(rs.getInt("year_made"));
				b.setBrand(rs.getString("brand"));
				bike.setModeltype(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setAvalibilty(rs.getString("status_avalibilty"));
				bike.setStatus(s);
				bikes.add(bike);
			}
		}catch(Exception e) {
			e.printStackTrace();

		}
		return bikes;
	}
	@Override
	public void update(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set color = ?, bike_size = ?,  model_id = ?, status_id = ? where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getColor());
			pstmt.setString(2, t.getSize());
			pstmt.setInt(3, t.getModeltype().getId());
			pstmt.setInt(4, t.getStatus().getId());
			pstmt.setInt(5, t.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete(Bike t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			
			// then, we can delete the cat
			String sql = "delete from bike where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
				}catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

