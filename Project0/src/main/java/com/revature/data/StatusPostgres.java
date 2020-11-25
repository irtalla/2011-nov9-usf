package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class StatusPostgres implements StatusDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Status add(Status t) throws NullPointerException{
		Status s = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into status values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1,  t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				s=t;
				s.setId(rs.getInt("id"));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Status getById(Integer id) throws NullPointerException {
		Status s = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from status where id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				s = new Status();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("status_name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public Set<Status> getAll() {
		Set<Status> sts = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from status";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Status s = new Status();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("status_name"));
				sts.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return sts;
	}

	@Override
	public void update(Status t) {
		try(Connection conn = cu.getConnection()){
			if(!t.equals(null)) {
				conn.setAutoCommit(false);
				String sql = "update status set name = ? where id = ?;";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  t.getName());
				pstmt.setInt(2, t.getId());
				int rowsChanged = pstmt.executeUpdate();
				
				if(rowsChanged > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Status t) {
		try(Connection conn = cu.getConnection()){
			String sql = "delete from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			int rowsChanged = pstmt.executeUpdate();
			if(rowsChanged > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
