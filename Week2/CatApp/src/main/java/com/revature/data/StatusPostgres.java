package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;


public class StatusPostgres implements StatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Status add(Status t) {
		// TODO Auto-generated method stub
		Status s = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into status values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				s = t;
				s.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
		
	}

	@Override
	public Status getById(Integer id) {
		// TODO Auto-generated method stub
		Status s = null;
		
		try (Connection conn = cu.getConnection()){
			String sql = "select * from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			return s;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Set<Status> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Status t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Status t) {
		// TODO Auto-generated method stub
		try(Connection conn = cu.getConnection()){
			String sql = "delete * from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
