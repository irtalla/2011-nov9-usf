package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.OfferStatus;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Offer add(Offer t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1,t.getOwner());
			pstmt.setInt(2, t.getItem());
			pstmt.setInt(3, t.getStatus().getID());
			pstmt.setFloat(4,t.getPrice());
			
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
	public Offer getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Offer newOffer = new Offer();
				
				newOffer.setID(rs.getInt("id"));
				newOffer.setPrice(rs.getFloat("price"));
				newOffer.setOwner(rs.getInt("customer_id"));
				newOffer.setItem(rs.getInt("bicycle_id"));
				
				/*
				newOffer.setOwner(DAOFactory.getCustomerDAO().getById(rs.getInt("customer_id")));
				newOffer.setItem(DAOFactory.getBikeDAO().getById(rs.getInt("bicycle_id")));
				*/
				
				sql = "select * from offer_status where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("status_id"));
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					
					OfferStatus stat = new OfferStatus();
					stat.setID(rs.getInt("id"));
					stat.setName(rs.getString("name"));
					newOffer.setStatus(stat);
					
					return newOffer;
				}
				else
					conn.rollback();
				
				
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
	public Set<Offer> getAll() {
		Set<Offer> results = new HashSet<>();
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from offer";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				Offer newOffer = new Offer();
				
				newOffer.setID(rs.getInt("id"));
				newOffer.setPrice(rs.getFloat("price"));
				newOffer.setOwner(rs.getInt("customer_id"));
				newOffer.setItem(rs.getInt("bicycle_id"));
				
				sql = "select * from offer_status where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("status_id"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next())
				{
					
					OfferStatus stat = new OfferStatus();
					stat.setID(rs2.getInt("id"));
					stat.setName(rs2.getString("name"));
					newOffer.setStatus(stat);
					
					results.add(newOffer);
				}
				else
					conn.rollback();
				
				
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
	public void update(Offer t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update offer set bicycle_id = ?, customer_id = ?, status_id = ?, price = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getItem());
			pstmt.setInt(2, t.getOwner());
			pstmt.setInt(3, t.getStatus().getID());
			pstmt.setFloat(4,t.getPrice());
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

	@Override
	public void delete(Offer t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "delete from offer where id = ?";
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

	@Override
	public void AcceptOffer(Offer t) {
		OfferStatus accepted = new OfferStatus();
		accepted.setID(1);
		accepted.setName("accepted");
		t.setStatus(accepted);
		
		Connection conn = cu.getConnection();
		try
		{
			conn.setAutoCommit(false);
			String sql = "update offer set status_id = 2 where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getItem());
			
			pstmt.executeUpdate();
			conn.commit();
			update(t);
			
			//give bike to customer
			sql = "insert into customer_bicycle values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getOwner());
			pstmt.setInt(2, t.getItem());
			pstmt.executeUpdate();
			conn.commit();
			
			//charge customer account (payment over 8 weeks)
			sql = "update customer set balance = balance + ?, weekly_payment = weekly_payment + ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getPrice());
			pstmt.setFloat(2, Math.round(t.getPrice()/8f*100f)/100f);
			pstmt.setInt(3, t.getOwner());
			
			pstmt.executeUpdate();
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
	public void RejectOffer(Offer t) {
		OfferStatus rejected = new OfferStatus();
		rejected.setID(2);
		rejected.setName("rejected");
		t.setStatus(rejected);
		update(t);
		
	}

}
