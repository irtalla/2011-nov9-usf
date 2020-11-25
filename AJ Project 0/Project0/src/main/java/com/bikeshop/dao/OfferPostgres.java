package com.bikeshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Offer;
import com.bikeshop.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Offer add(Offer o) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO bikeshop.offer("
					+ "id, bikeid, personid, amount, weeks)"
					+ "values (default, ?, ?, ?, ?);";
			
			String[] keys = {"id"};
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			pst.setInt(1, o.getBikeID());
			pst.setInt(2, o.getPersonID());
			pst.setFloat(3, o.getAmount());
			pst.setInt(4, o.getWeeks());
			

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				o.setId(rs.getInt(1));
				conn.commit();
				return o;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Offer getByID(Integer id) {
		Offer o = new Offer();
		 
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.offer where id = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				o.setId(rs.getInt("id"));
				o.setBikeID(rs.getInt("bikeid"));
				o.setPersonID(rs.getInt("personid"));
				o.setAmount(rs.getFloat("amount"));
				o.setWeeks(rs.getInt("weeks"));
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public boolean updateOffer(Offer o) {
		boolean updated = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bikeshop.offer set bikeid=?, personid=?, amount=?, weeks=? where id = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, o.getBikeID());
			pst.setInt(2, o.getPersonID());
			pst.setFloat(3, o.getAmount());
			pst.setInt(4, o.getWeeks());
			pst.setInt(5, o.getId());
			
			
			pst.executeUpdate();
			
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				updated = true;
				conn.commit();
			} else conn.rollback();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean delete(Integer id) {
		boolean deleted = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bikeshop.offer where id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
				conn.commit();
			} else conn.rollback();
			return deleted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return deleted;
	}

	public boolean deleteByBikeID(Integer id) {
		boolean deleted = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bikeshop.offer where bikeid = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
				conn.commit();
			} else conn.rollback();
			return deleted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return deleted;
	}
	
	@Override
	public List<Offer> getByBike(Bike b) {
		List<Offer> offers = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.offer where bikeid = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, b.getId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setBikeID(rs.getInt("bikeid"));
				o.setPersonID(rs.getInt("personid"));
				o.setAmount(rs.getFloat("amount"));
				o.setWeeks(rs.getInt("weeks"));
				offers.add(o);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public List<Offer> getByBikeID(Integer id) {
		List<Offer> offers = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.offer where bikeid = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setBikeID(rs.getInt("bikeid"));
				o.setPersonID(rs.getInt("personid"));
				o.setAmount(rs.getFloat("amount"));
				o.setWeeks(rs.getInt("weeks"));
				offers.add(o);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}
	
	public List<Offer> getByPersonID(Integer id) {
		List<Offer> offers = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.offer where personid = ?;";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setBikeID(rs.getInt("bikeid"));
				o.setPersonID(rs.getInt("personid"));
				o.setAmount(rs.getFloat("amount"));
				o.setWeeks(rs.getInt("weeks"));
				offers.add(o);
//				System.out.println(o);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}
	
	public List<Offer> getAllOffers() {
		List<Offer> offers = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from bikeshop.offer;";
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setBikeID(rs.getInt("bikeid"));
				o.setPersonID(rs.getInt("personid"));
				o.setAmount(rs.getFloat("amount"));
				o.setWeeks(rs.getInt("weeks"));
				offers.add(o);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}

}
