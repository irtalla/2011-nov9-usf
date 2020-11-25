package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Accessory;
import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Offer;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

public class CustomerPostgres implements CustomerDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Customer add(Customer t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into customer values (default, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			//customers are id 0, employees 1, managers 2
			pstmt.setInt(3, t.getRole().getId());
			pstmt.setFloat(4,t.getBalance());
			pstmt.setFloat(5, t.getWeeklyPayment());
			pstmt.setString(6, t.getName());
			
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
	public Customer getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from customer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Customer c = new Customer();
				
				
				
				c.setID(rs.getInt("id"));
				c.setBalance(rs.getFloat("balance"));
				c.setName(rs.getString("name"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("passwd"));
				c.setWeeklyPayment(rs.getFloat("weekly_payment"));
				
				Role r = new Role();
				sql = "select * from user_role where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("user_role_id"));
				
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					r.setName(rs.getString("name"));
					r.setId(rs.getInt("id"));
					c.setRole(r);
				}
				else
				{
					throw new Exception("could not get role id");
				}
				
				
				
				sql = "select * from offer where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					
					
					Offer o = DAOFactory.getOfferDAO().getById(rs.getInt("id"));
					
					c.getOffers().add(o);
				}
				
				
				sql = "select * from customer_bicycle where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					
					Bicycle b = DAOFactory.getBikeDAO().getById(rs.getInt("bicycle_id"));
					
					c.getGarage().add(b);
				}
				
				sql = "select * from customer_accessory where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					Accessory a = DAOFactory.getAccessoryDAO().getById(rs.getInt("accessory_id"));
					c.getCart().put(a, rs.getInt("num_owned"));
				}
				
				return c;
				
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
	public Set<Customer> getAll() {
		Connection conn = cu.getConnection();
		Set<Customer> results = new HashSet<>();
		
		try
		{
			String sql = "select * from customer";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while (rs.next())
			{
				Customer c = new Customer();
				
				
				
				c.setID(rs.getInt("id"));
				c.setBalance(rs.getFloat("balance"));
				c.setName(rs.getString("name"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("passwd"));
				c.setWeeklyPayment(rs.getFloat("weekly_payment"));
				
				Role r = new Role();
				sql = "select * from user_role where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("user_role_id"));
				
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next())
				{
					r.setName(rs2.getString("name"));
					r.setId(rs2.getInt("id"));
					c.setRole(r);
				}
				else
				{
					throw new Exception("could not get role id");
				}
				
				
				
				
				sql = "select * from offer where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c.getID());
				rs2 = pstmt.executeQuery();
				
				while (rs2.next())
				{
					
					
					Offer o = DAOFactory.getOfferDAO().getById(rs2.getInt("id"));
					
					c.getOffers().add(o);
				}
				
				
				sql = "select * from customer_bicycle where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c.getID());
				rs2 = pstmt.executeQuery();
				
				while (rs2.next())
				{
					
					Bicycle b = DAOFactory.getBikeDAO().getById(rs2.getInt("bicycle_id"));
					
					c.getGarage().add(b);
				}
				
				sql = "select * from customer_accessory where customer_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c.getID());
				rs2 = pstmt.executeQuery();
				
				while (rs2.next())
				{
					Accessory a = DAOFactory.getAccessoryDAO().getById(rs2.getInt("accessory_id"));
					
					c.getCart().put(a, rs2.getInt("num_owned"));
				}
				
				results.add(c);
				
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
	public void update(Customer t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update customer set username = ?, passwd = ?, user_role_id = ?, balance = ?, weekly_payment = ?, name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.setFloat(4, t.getBalance());
			pstmt.setFloat(5, t.getWeeklyPayment());
			pstmt.setString(6, t.getName());
			pstmt.setInt(7, t.getID());
			
			
			pstmt.execute();
			/*
			Set<Accessory> accs = t.getCart().keySet();
			for (Accessory a : accs)
			{
				sql = "insert into customer_accessory (num_owned, customer_id, accessory_id) values (?,?,?) on conflict do update customer_accessory set num_owned = ? "
						+ "where customer_id = ? and accessory_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getCart().get(a));
				pstmt.setInt(2, t.getID());
				pstmt.setInt(3, a.getID());
				pstmt.setInt(4, t.getCart().get(a));
				pstmt.setInt(5, t.getID());
				pstmt.setInt(6, a.getID());
				
				pstmt.execute();
			}
			*/
			
			for (Offer o: t.getOffers())
			{
				DAOFactory.getOfferDAO().update(o);
			}
			
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
	public void delete(Customer t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			
			String sql = "delete from customer_bicycle where customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from customer_accessory where customer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from offer where customer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getID());
			
			pstmt.executeUpdate();
			
			sql = "delete from customer where id = ?";
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
	public Customer getByLoginCredentials(String username, String password) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select id from customer where username = ? and passwd = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				return getById(rs.getInt("id"));
			}
			else
			{
				return null;
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

	@Override
	public Boolean verifyUniqueUsername(String username) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select id from customer where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				return false;
			}
			else
			{
				return true;
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

}
