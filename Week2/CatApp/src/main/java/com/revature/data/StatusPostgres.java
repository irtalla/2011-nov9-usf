package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class StatusPostgres implements StatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Status add(Status t) {
<<<<<<< HEAD
		// Currently, there is no reason to create a new status
		return null;
=======
		Status s = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into status values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				s=t;
				s.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
>>>>>>> 5db0220dbef16c9e53acdb9ce1cabe080e7badce
	}

	@Override
	public Status getById(Integer id) {
<<<<<<< HEAD
		try {
			
		}
=======
		Status s = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				s = new Status();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
>>>>>>> 5db0220dbef16c9e53acdb9ce1cabe080e7badce
	}

	@Override
	public Set<Status> getAll() {
		Set<Status> statuses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from status";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Status s = new Status();	
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				statuses.add(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void update(Status t) {
		try (Connection conn = cu.getConnection()) {
			if(!(t.equals(null))) {
				conn.setAutoCommit(false);
				String sql = "Update status set name = ? where id = ?";
				
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
	public void delete(Status t) {
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from status where id = ?";
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
