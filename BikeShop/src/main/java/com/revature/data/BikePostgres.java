package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Bicycle add(Bicycle t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setFloat(1, t.getPrice());
			pstmt.setString(2, t.getModel());
			pstmt.setString(3, t.getBrand());
			
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

	@Override
	public Bicycle getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from bicycle where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Bicycle bike = new Bicycle();
				bike.setID(rs.getInt("id"));
				bike.setPrice(rs.getFloat("base_price"));
				bike.setModel(rs.getString("model"));
				bike.setBrand(rs.getString("brand"));
				
				
				
				
				
				
				sql = "select * from offer where bicycle_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					
					
					Offer o = DAOFactory.getOfferDAO().getById(rs.getInt("id"));
					
					bike.getOffers().add(o);
				}
				return bike;
				
			}
			else
				conn.rollback();
			
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

	@Override
	public Set<Bicycle> getAll() {
		Connection conn = cu.getConnection();
		Set<Bicycle> results = new HashSet<Bicycle>();
		
		try
		{
			String sql = "select * from bicycle";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while (rs.next())
			{
				Bicycle bike = new Bicycle();
				bike.setID(rs.getInt("id"));
				bike.setPrice(rs.getFloat("base_price"));
				bike.setModel(rs.getString("model"));
				bike.setBrand(rs.getString("brand"));
				
				
				
				
				
				
				sql = "select * from offer where bicycle_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bike.getID());
				ResultSet rs2 = pstmt.executeQuery();
				
				while (rs2.next())
				{
					
					
					Offer o = DAOFactory.getOfferDAO().getById(rs2.getInt("id"));
					
					bike.getOffers().add(o);
				}
				results.add(bike);
				
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
		
		
		return results;
	}

	@Override
	public void update(Bicycle t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update bicycle set base_price = ?, model = ?, brand = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getPrice());
			pstmt.setString(2, t.getModel());
			pstmt.setString(3, t.getBrand());
			pstmt.setInt(4, t.getID());
			
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

	@Override
	public void delete(Bicycle t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			
			String sql = "delete from customer_bicycle where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from offer where bicycle_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from bicycle where id = ?";
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

}
