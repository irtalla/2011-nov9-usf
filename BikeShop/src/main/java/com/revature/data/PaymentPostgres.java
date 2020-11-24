package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Payment;
import com.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Payment add(Payment t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into payment values (default, ?, ?, current_date)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1,t.getCustomerID());
			pstmt.setFloat(2, t.getAmount());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) 
			{

				t.setID(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
			sql = "select payment_date from payment where id = ?";
			pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1,t.getID());
			
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				t.setDate(rs.getString("payment_date"));
			}
			else
			{
				conn.rollback();
			}
			conn.commit();
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
	public Payment getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Payment newPayment = new Payment();
				
				newPayment.setID(rs.getInt("id"));
				newPayment.setAmount(rs.getFloat("amount"));
				newPayment.setCustomerID(rs.getInt("customer_id"));
				newPayment.setDate(rs.getString("payment_date"));
				
				return newPayment;
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
	public Set<Payment> getAll() {
		Connection conn = cu.getConnection();
		Set<Payment> results = new HashSet<Payment>();
		
		try
		{
			String sql = "select * from payment";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				Payment newPayment = new Payment();
				
				newPayment.setID(rs.getInt("id"));
				newPayment.setAmount(rs.getFloat("amount"));
				newPayment.setCustomerID(rs.getInt("customer_id"));
				newPayment.setDate(rs.getString("payment_date"));
				
				results.add(newPayment);
				
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
	public void update(Payment t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update payment set customer_id = ?, payment_date = current_date, amount = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getCustomerID());
			pstmt.setFloat(2,t.getAmount());
			pstmt.setInt(3, t.getID());
			
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
	public void delete(Payment t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "delete from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
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
