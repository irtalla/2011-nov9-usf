package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.Bike;
import com.revature.models.InventoryStatus;
import com.revature.models.InventoryType;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			
	@Override
	public Bike add(Bike t) {
		Bike b = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into inventory values "
					+ "(default, ?, "
					+ "(select it.id from inventory_type as it where it.id = ?), "
					+ "(select ist.id from inventory_status as ist where ist.id = ?)"
					+ ")";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setFloat(1, t.getValue());
			pstmt.setInt(2, t.getType().getId());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				b = t;
				b.setId(rs.getInt(1));
				
				sql = "insert into users_inventory values "
						+ "(default, "
						+ "(select id from users where id = 2), "
						+ "(select id from inventory where id = ?))";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b.getId());
				pstmt.executeUpdate();
				
				conn.commit();				
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public Bike getById(Integer id) {
		Bike b = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "and i.id = " + id; 
			Set<Bike> bikes = getBikeInfo(sql, conn);
			for (Bike bike : bikes) {
				if (bike.getId().equals(id)) {
					b = bike;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			bikes = getBikeInfo(sql, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bikes;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "and s.name = 'Available'";
			bikes = getBikeInfo(sql, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bikes;
	}

	@Override
	public void update(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update inventory set value = ?, "
					+ "type_id = (select it.id from inventory_type as it where it.id = ?), "
					+ "status_id = (select ist.id from inventory_status as ist where ist.id = ?)"
					+ "where inventory.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getValue());
			pstmt.setInt(2, t.getType().getId());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getId());		
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from users_inventory where inventory_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected >= 0) {
				sql = "delete from inventory where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());				
				rowsAffected += pstmt.executeUpdate();
				
				if(rowsAffected > 0)
					conn.commit();		
				else
					conn.rollback();	
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Set<Bike> getBikeInfo(String additionalString, Connection conn) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		String sql = "select i.id as bike_id, i.value as bike_value, "
				+ "s.id as status_id, s.name as status_name, "
				+ "t.id as type_id, t.name as type_name "
				+ "from ((inventory as i join inventory_status as s on i.status_id = s.id) "
				+ "join inventory_type as t on i.type_id = t.id) "
				+ "where t.name = 'Bike' "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike b = new Bike();
			b.setId(rs.getInt("bike_id"));
			b.setValue(rs.getFloat("bike_value"));
			InventoryStatus is = new InventoryStatus();
			is.setId(rs.getInt("status_id"));
			is.setName(rs.getString("status_name"));
			b.setStatus(is);
			InventoryType it = new InventoryType();
			it.setId(rs.getInt("type_id"));
			it.setName(rs.getString("type_name"));
			b.setType(it);
			bikes.add(b);

		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		return bikes;
	}
	
}
