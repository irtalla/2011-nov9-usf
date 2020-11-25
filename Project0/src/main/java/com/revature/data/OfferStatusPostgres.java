package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.InventoryStatus;
import com.revature.models.OfferStatus;
import com.revature.utils.ConnectionUtil;

public class OfferStatusPostgres implements OfferStatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public OfferStatus add(OfferStatus t) {
		OfferStatus os = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer_status values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				os = t;
				os.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return os;
	}
	
	@Override
	public OfferStatus getById(Integer id) {
		OfferStatus os = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer_status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				os = new OfferStatus();
				os.setId(rs.getInt("id"));
				os.setName(rs.getString("name"));
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return os;
	}

	@Override
	public Set<OfferStatus> getAll() {
		Set<OfferStatus> statuses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer_status";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				OfferStatus os = new OfferStatus();	
				os.setId(rs.getInt("id"));
				os.setName(rs.getString("name"));
				statuses.add(os);
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void update(OfferStatus t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offer_status set name = ? where id = ?";
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
	public void delete(OfferStatus t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from offer_status where id = ?";
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
