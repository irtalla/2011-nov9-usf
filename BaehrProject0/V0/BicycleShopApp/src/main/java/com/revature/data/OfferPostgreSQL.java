package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.Offer;
import com.revature.util.ConnectionUtil;

public class OfferPostgreSQL implements OfferDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Set<Offer> getByBicycleId(Integer id) { //gets all outstanding offers for a given bicycle ID
		Set<Offer> offers = new HashSet<Offer>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select o.offer_id,  o.offer, o.user_id, o.status, o.bicycle_id, b.Price, b.year, b.status, "
						+ " b.owner_id, b.type_id, bt.manufacturer, bt.model  "
						+ " from offers o "
						+ " join bicycles b on o.bicycle_id = b.bicycle_id  "
						+ " join bicycle_types bt on bt.type_id = b.type_id "
						+ " where b.bicycle_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Offer o = new Offer();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(10));
					bt.setManufacturer(rs.getString(11));
					bt.setModel(rs.getString(12));
					b.setBicycleType(bt);
					b.setId(rs.getInt(5));
					b.setYear(rs.getInt(7));
					b.setPrice(rs.getDouble(6));
					b.setOwner_id(rs.getInt(9));
					b.setStatus(rs.getString(8));
					o.setBicycle(b);
					o.setOffer_id(rs.getInt(1));
					o.setOffer(rs.getDouble(2));
					o.setUser_id(rs.getInt(3));
					o.setStatus(rs.getString(4));
					offers.add(o);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return offers;
	}

	@Override
	public void update(Offer t) {
		if(t == null) {
			System.out.println("Cannot update a null offer to the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update offers "
						+" set bicycle_id = ?, "
						+" user_id = ?, "
						+" offer = ?, "
						+" status = ?"
						+" where offer_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getBicycle().getId());
				pstmt.setInt(2, t.getUser_id());
				pstmt.setDouble(3, t.getOffer());
				pstmt.setString(4, t.getStatus());
				pstmt.setInt(5, t.getOffer_id());
				
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(Offer t) {
		if(t == null) {
			System.out.println("Cannot delete a null offer from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from offers where offer_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getOffer_id());
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Offer add(Offer o) {
		if(o == null) {
			System.out.println("Cannot add a null user to the database");
		}
		else {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into offers values(default, ?, ?, ?, 'Pending')";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setInt(1,  o.getBicycle().getBicycleType().getId());
			pstmt.setInt(2,  o.getUser_id());
			pstmt.setDouble(3,  o.getOffer());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				o.setOffer_id(rs.getInt(1));
				conn.commit();
				//System.out.println("Committed");
			}
			else {
				conn.rollback();
			}
			
			}
			catch(Exception e) {
			e.printStackTrace();
			}
		}
		return o;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<Offer>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select o.offer_id,  o.offer, o.user_id, o.status, o.bicycle_id, b.Price, b.year, "
						+ " b.status, b.owner_id, b.type_id, bt.manufacturer, bt.model  "
						+ " from offers o "
						+ " join bicycles b on o.bicycle_id = b.bicycle_id  "
						+ " join bicycle_types bt on bt.type_id = b.type_id ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Offer o = new Offer();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(10));
					bt.setManufacturer(rs.getString(11));
					bt.setModel(rs.getString(12));
					b.setBicycleType(bt);
					b.setId(rs.getInt(5));
					b.setYear(rs.getInt(7));
					b.setPrice(rs.getDouble(6));
					b.setOwner_id(rs.getInt(9));
					b.setStatus(rs.getString(8));
					o.setBicycle(b);
					o.setOffer_id(rs.getInt(1));
					o.setOffer(rs.getDouble(2));
					o.setUser_id(rs.getInt(3));
					o.setStatus(rs.getString(4));
					offers.add(o);
					offers.add(o);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return offers;
	}

	@Override
	public Offer getById(Integer id) {
		Offer o = null;
		try (Connection conn = cu.getConnection()){
				Offer n = new Offer();
				conn.setAutoCommit(false);
				String sql = "select o.offer_id, o.offer, o.user_id, o.status, o.bicycle_id, b.price, "
						+ " b.status, b.year, b.owner_id, b.type_id, bt.manufacturer, bt.model " 
						+ " from offers o join bicycles b on b.bicycle_id = o.bicycle_id " 
						+ " join bicycle_types bt on bt.type_id = b.type_id"
						+ " where o.offer_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				//System.out.println(rs.next());
				if(rs.next()) {
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(10));
					bt.setManufacturer(rs.getString(11));
					bt.setModel(rs.getString(12));
					b.setBicycleType(bt);
					
					b.setId(rs.getInt(5));
					b.setYear(rs.getInt(8));
					b.setPrice(rs.getDouble(6));
					b.setOwner_id(rs.getInt(9));
					b.setStatus(rs.getString(7));
					n.setBicycle(b);
					n.setOffer(rs.getDouble(2));
					n.setOffer_id(rs.getInt(1));
					n.setStatus(rs.getString(4));
					n.setUser_id(rs.getInt(3));
					o = n;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return o;
	}

	@Override
	public Set<Offer> getByUserId(Integer id) {
		Set<Offer> offers = new HashSet<Offer>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select o.offer_id,  o.offer, o.user_id, o.status, o.bicycle_id, b.Price, b.year, b.status, "
						+ " b.owner_id, b.type_id, bt.manufacturer, bt.model  "
						+ " from offers o "
						+ " join bicycles b on o.bicycle_id = b.bicycle_id  "
						+ " join bicycle_types bt on bt.type_id = b.type_id "
						+ " where o.user_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Offer o = new Offer();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(10));
					bt.setManufacturer(rs.getString(11));
					bt.setModel(rs.getString(12));
					b.setBicycleType(bt);
					b.setId(rs.getInt(5));
					b.setYear(rs.getInt(7));
					b.setPrice(rs.getDouble(6));
					b.setOwner_id(rs.getInt(9));
					b.setStatus(rs.getString(8));
					o.setBicycle(b);
					o.setOffer_id(rs.getInt(1));
					o.setOffer(rs.getDouble(2));
					o.setUser_id(rs.getInt(3));
					o.setStatus(rs.getString(4));
					offers.add(o);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return offers;
	}

	@Override
	public Offer acceptOffer(Offer o) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);

			//System.out.println(o);
			String sql2 = "update offers " 
				+ "	set status = 'Rejected' "
				+ "	where bicycle_id = ? and offer_id != ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, o.getBicycle().getId());
			pstmt2.setInt(2, o.getOffer_id());
		
			int r2 = pstmt2.executeUpdate();
		
			if(r2 > 0) {
				//System.out.println(r2);
				conn.commit();
			}
			else {
				conn.rollback();
			}
			//System.out.println(o);
			String sql = "update offers "
					+" set status = 'Accepted' "
					+" where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getOffer_id());
			
			int r = pstmt.executeUpdate();
			//System.out.println(r);
			//System.out.println(o);
			if(r > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
			return o;
	}

	@Override
	public Set<Offer> getOutstanding() {
		Set<Offer> offers = new HashSet<Offer>();
		try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "select o.offer_id,  o.offer, o.user_id, o.status, o.bicycle_id, b.Price, b.year, "
						+ " b.status, b.owner_id, b.type_id, bt.manufacturer, bt.model  "
						+ " from offers o "
						+ " join bicycles b on o.bicycle_id = b.bicycle_id  "
						+ " join bicycle_types bt on bt.type_id = b.type_id "
						+ " where o.status = 'Pending' ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Offer o = new Offer();
					Bicycle b = new Bicycle();
					BicycleType bt = new BicycleType();
					bt.setId(rs.getInt(10));
					bt.setManufacturer(rs.getString(11));
					bt.setModel(rs.getString(12));
					b.setBicycleType(bt);
					b.setId(rs.getInt(5));
					b.setYear(rs.getInt(7));
					b.setPrice(rs.getDouble(6));
					b.setOwner_id(rs.getInt(9));
					b.setStatus(rs.getString(8));
					o.setBicycle(b);
					o.setOffer_id(rs.getInt(1));
					o.setOffer(rs.getDouble(2));
					o.setUser_id(rs.getInt(3));
					o.setStatus(rs.getString(4));
					offers.add(o);
					offers.add(o);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return offers;
	}
	
//	@Override
//	public void rejectOtherOffers(Offer o) {
//		try (Connection conn = cu.getConnection()){
//			conn.setAutoCommit(false);
//			String sql = "update offers " 
//					+ "	set status = 'Rejected' "
//					+ "	where bicycle_id = ? and offer_id != ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, o.getBicycle().getId());
//			pstmt.setInt(2, o.getOffer_id());
//			//System.out.println(o.getOffer_id());
//			int r = pstmt.executeUpdate();
//			
//			if(r > 0) {
//				conn.commit();
//			}
//			else {
//				conn.rollback();
//			}
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
