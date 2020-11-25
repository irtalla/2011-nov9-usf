package dev.warrington.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.warrington.beans.Bicycle;
import dev.warrington.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bicycle add(Bicycle t) {
		
		Bicycle b = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bicycles values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getManufacturer());
			pstmt.setString(2, t.getModel());
			pstmt.setDouble(3, t.getAskingPrice());
			pstmt.setInt(4, 1);
			pstmt.setString(5, t.getColor());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				b = t;
				b.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public Bicycle getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bicycle> getAll() {
		
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Bicycle b;
			
			String sql = "select * from bicycles";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				b = new Bicycle(rs.getString("manufacturer"), rs.getString("model"), rs.getDouble("asking_price"), rs.getString("color"));
				b.setId(rs.getInt("id"));
				b.setStatus(rs.getInt("status"));
				
				bikes.add(b);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return bikes;
	}

	@Override
	public void update(Bicycle t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bicycle t) {
		
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from bicycles where id = " + t.getId();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Bicycle> getAvailable() {
		
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Bicycle b;
			
			String sql = "select * from bicycles where status = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				b = new Bicycle(rs.getString("manufacturer"), rs.getString("model"), rs.getDouble("asking_price"), rs.getString("color"));
				b.setId(rs.getInt("id"));
				b.setStatus(rs.getInt("status"));
				bikes.add(b);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return bikes;
		
	}
	
	public Set<Bicycle> getMine(Integer id) {
		
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			
			Bicycle b;
			
			String sql = "select id, manufacturer, model, asking_price, color from bicycles join bicycle_owners on id = bicycle_id where person_id =" + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				b = new Bicycle(rs.getString("manufacturer"), rs.getString("model"), rs.getDouble("asking_price"), rs.getString("color"));
				b.setId(rs.getInt("id"));
				bikes.add(b);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return bikes;
	}

	@Override
	public void updateBicycleStatus(Integer id) {
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bicycles set status = 2 where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
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
	public void updateOwners(Integer bid, Integer cid) {
		
		try (Connection conn = cu.getConnection()) {
			
			String sql = "insert into bicycle_owners values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setInt(2, cid);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}