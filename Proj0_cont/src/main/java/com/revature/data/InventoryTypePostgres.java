package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.InventoryType;
import com.revature.utils.ConnectionUtil;

public class InventoryTypePostgres implements InventoryTypeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public InventoryType add(InventoryType t) {
		InventoryType it = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into inventory_type values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				it = t;
				it.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return it;
		
	}
	
	@Override
	public InventoryType getById(Integer id) {
		InventoryType it = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from inventory_type where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				it = new InventoryType();
				it.setId(rs.getInt("id"));
				it.setName(rs.getString("name"));
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return it;
		
	}

	@Override
	public Set<InventoryType> getAll() {
		Set<InventoryType> types = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from inventory_type";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				InventoryType it = new InventoryType();
				it.setId(rs.getInt("id"));
				it.setName(rs.getString("name"));
				types.add(it);
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return types;
	}

	@Override
	public void update(InventoryType t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update inventory_type set name = ? where id = ?";
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
	public void delete(InventoryType t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from inventory_type where id = ?";
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
