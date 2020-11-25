package com.james.data;

import java.util.Set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import com.james.beans.Bike;
import com.james.exceptions.NonUniqueUsernameException;
import com.james.beans.Role;
import com.james.exceptions.NonUniqueUsernameException;
import com.james.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
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
	public void update(Bike t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Bike t) {
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from bike where name= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());		
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}

	@Override
	public Bike add(Bike t) throws NonUniqueUsernameException {
		Bike  b= null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getCondition());
			pstmt.setInt(3, 2);
			pstmt.setInt(4, 1);
			
			
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
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();			
		}
		
		return b;
	}
	
	@Override
	public void view() {
		try (Connection conn =  cu.getConnection()) {
			String sql = "select * from bike where bike_status_id='2'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("id     name");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println(id + "    " + name + "    ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
