package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.OfferStatus;
import com.revature.models.PaymentStatus;
import com.revature.utils.ConnectionUtil;

public class PaymentStatusPostgres implements PaymentStatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public PaymentStatus add(PaymentStatus t) {
		PaymentStatus ps = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into payment_status values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				ps = t;
				ps.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			
		}
		
		return ps;
	}
	
	@Override
	public PaymentStatus getById(Integer id) {
		PaymentStatus ps = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from payment_status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ps = new PaymentStatus();
				ps.setId(rs.getInt("id"));
				ps.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ps;
	}

	@Override
	public Set<PaymentStatus> getAll() {
		Set<PaymentStatus> statuses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from payment_status";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				PaymentStatus ps = new PaymentStatus();	
				ps.setId(rs.getInt("id"));
				ps.setName(rs.getString("name"));
				statuses.add(ps);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void update(PaymentStatus t) {
		try (Connection conn = cu.getConnection()) {
			if(!(t.equals(null))) {
				conn.setAutoCommit(false);
				String sql = "update payment_status set name = ? where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getName());
				pstmt.setInt(2, t.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(PaymentStatus t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from payment_status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
