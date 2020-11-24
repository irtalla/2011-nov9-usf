package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Accessory;
import com.revature.utils.ConnectionUtil;

public class AccessoryPostgres implements AccessoryDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	
	public Accessory add(Accessory t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into accessory values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getCategory());
			pstmt.setString(3, t.getBrand());
			pstmt.setDouble(4,t.getPrice());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) 
			{
				
				t.setID(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} 
		catch (Exception e) 
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return t;
	}

	public Accessory getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from accessory where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Integer resId = rs.getInt("id");
				String resName = rs.getString("name");
				String resCategory = rs.getString("category");
				String resBrand = rs.getString("brand");
				Float resPrice = rs.getFloat("price");
				return new Accessory(resId, resPrice, resName, resCategory, resBrand);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

	public Set<Accessory> getAll() {
		Connection conn = cu.getConnection();
		Set<Accessory> result = new HashSet<>();
		
		try
		{
			String sql = "select * from accessory";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				Integer resId = rs.getInt("id");
				String resName = rs.getString("name");
				String resCategory = rs.getString("category");
				String resBrand = rs.getString("brand");
				Float resPrice = rs.getFloat("price");
				result.add(new Accessory(resId, resPrice, resName, resCategory, resBrand));
			}
			return result;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public void update(Accessory t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update accessory set name = ?, category = ?, brand = ?, price = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getCategory());
			pstmt.setString(3, t.getBrand());
			pstmt.setDouble(4,t.getPrice());
			pstmt.setInt(5, t.getID());
			
			pstmt.execute();
			conn.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delete(Accessory t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "delete from customer_accessory where accessory_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from accessory where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			conn.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void purchase(Accessory t, Integer customerID, Integer num) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "insert into customer_accessory values (?, ?, ?) on conflict ("
					+ "update customer_accessory set num_owned = num_owned + ? where customer_id = ? and accessory_id = ?";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
