package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.InventoryStatus;
import com.revature.utils.ConnectionUtil;

public class InventoryStatusPostgres implements InventoryStatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public InventoryStatus add(InventoryStatus t) {
		InventoryStatus is = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into inventory_status values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				is = t;
				is.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return is;
	}

	@Override
	public InventoryStatus getById(Integer id) {
		InventoryStatus is = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from inventory_status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				is = new InventoryStatus();
				is.setId(rs.getInt("id"));
				is.setName(rs.getString("name"));
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return is;
	}

	@Override
	public Set<InventoryStatus> getAll() {
		Set<InventoryStatus> statuses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from inventory_status";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				InventoryStatus s = new InventoryStatus();	
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				statuses.add(s);
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void update(InventoryStatus t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update inventory_status set name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getId());
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
	public void delete(InventoryStatus t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from inventory_status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
