package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.utils.ConnectionUtil;



public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Bike add(Bike t) {
		// TODO Auto-generated method stub
		Bike b = null;

		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into Bike values (default, ?, ?, ?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getModel().getId());
			pstmt.setDouble(3, t.getPrice());
			pstmt.setInt(4, t.getStatus().getId());
			

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
		return null;
	}

	@Override
	public Bike getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		
		return null;
	}
	
	@Override
	public void update(Bike t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Bike t) {
		// TODO Auto-generated method stub

	}

}
