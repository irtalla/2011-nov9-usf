package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Category;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BicyclePostgres implements BicycleDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Bicycle getById(Integer id) {
		Bicycle bicycle = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select bicycle_status.id, bicycle_status.model, bicycle_status.category_id, bicycle_status.category_name, status.id as status_id, status_name from\r\n"
					+ "(select bicycle.id, model , category.id as category_id, \r\n"
					+ "category_name, status  from bicycle join category on bicycle.category = category.id where bicycle.id = ?) as bicycle_status\r\n"
					+ "join status on bicycle_status.status = status.id ";
			PreparedStatement pstmt =  conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			pstmt.executeQuery();

			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				Bicycle retBicycle = new Bicycle();
				retBicycle.setId(rs.getInt("id"));
				retBicycle.setModelName(rs.getString("model"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				retBicycle.setCategory(category);
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setStatus(rs.getString("status_name"));
				retBicycle.setStatus(status);
				bicycle = retBicycle;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bicycle;
	}

	@Override
	public Set<Bicycle> getAll() {
		Set<Bicycle> bicycles = new HashSet<Bicycle>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select bicycle_status.id, bicycle_status.model, bicycle_status.category_id, bicycle_status.category_name, status.id as status_id, status_name from\r\n"
					+ "(select bicycle.id, model , category.id as category_id, \r\n"
					+ "category_name, status  from bicycle join category on bicycle.category = category.id) as bicycle_status\r\n"
					+ "join status on bicycle_status.status = status.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while (rs.next()) {
				Bicycle bicycle = new Bicycle();
				bicycle.setId(rs.getInt("id"));
				bicycle.setModelName(rs.getString("model"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				bicycle.setCategory(category);
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setStatus(rs.getString("status_name"));
				bicycle.setStatus(status);
				bicycles.add(bicycle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bicycles;
	}

	@Override
	public void update(Bicycle b) {

		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update bicycle set model = ?, category = ?, status = ? "
					+ "where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, b.getModelName());
			pstmt.setInt(2, b.getCategory().getId());
			pstmt.setInt(3, b.getStatus().getId());
			pstmt.setInt(4, b.getId());
			
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Bicycle t) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sqlString = "delete from bicycle where id = ?";
			String[] keyStrings = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keyStrings);
			pstmt.setInt(1, t.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resetDefault();
	}

	@Override
	public Bicycle add(Bicycle b) {
		Bicycle retValBicycle = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "insert into bicycle values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keys);
			pstmt.setString(1, b.getModelName());
			pstmt.setInt(2, b.getCategory().getId());
			pstmt.setInt(3, b.getStatus().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				retValBicycle = b;
				retValBicycle.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValBicycle;
	}

	@Override
	public Set<Bicycle> getAvailableBicycles() {
		Set<Bicycle> bicycles = new HashSet<Bicycle>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select bicycle_status.id, bicycle_status.model, bicycle_status.category_id, bicycle_status.category_name, status.id as status_id, status_name from\r\n"
					+ "(select bicycle.id, model , category.id as category_id, \r\n"
					+ "category_name, status  from bicycle join category on bicycle.category = category.id ) as bicycle_status\r\n"
					+ "join status on bicycle_status.status = status.id where status.id  = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while (rs.next()) {
				Bicycle bicycle = new Bicycle();
				bicycle.setId(rs.getInt("id"));
				bicycle.setModelName(rs.getString("model"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				bicycle.setCategory(category);
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setStatus(rs.getString("status_name"));
				bicycle.setStatus(status);
				bicycles.add(bicycle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bicycles;
	}
	
	@Override
	public void updateOwner(Bicycle b, Person p) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update bicycle set owned_by = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			if (p == null) {
				pstmt.setNull(1, java.sql.Types.INTEGER);
				pstmt.setInt(2, b.getId());
			}else {
				pstmt.setInt(1, p.getId());
				pstmt.setInt(2, b.getId());
			}
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Set<Bicycle> getOwnedBicycles(Person p) {
		Set<Bicycle> bicycles = new HashSet<Bicycle>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select bicycle_status.id, bicycle_status.model, bicycle_status.category_id, bicycle_status.category_name, status.id as status_id, status_name from\r\n"
					+ "(select bicycle.id, model , category.id as category_id, \r\n"
					+ "category_name, status  from bicycle join category on bicycle.category = category.id where bicycle.owned_by = ?) as bicycle_status\r\n"
					+ "join status on bicycle_status.status = status.id";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, p.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bicycle bicycle = new Bicycle();
				bicycle.setId(rs.getInt("id"));
				bicycle.setModelName(rs.getString("model"));
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				bicycle.setCategory(category);
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setStatus(rs.getString("status_name"));
				bicycle.setStatus(status);
				bicycles.add(bicycle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bicycles;
	}
	
	public void resetDefault() {
		try (Connection conn = cu.getConnection()){
			String sqlString = "select setval('bicycleshop.bicycle_id_seq', max(id)) FROM bicycle";
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sqlString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
