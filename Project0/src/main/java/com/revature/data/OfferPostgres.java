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
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private BicyclePostgres bicycleDAO = new BicyclePostgres();
	private PersonPostgres personDAO = new PersonPostgres();

	@Override
	public Offer add(Offer t){
		Offer o = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "insert into offer values (default, ?, ?, ?, ?)";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setDouble(1, t.getAmount());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.setInt(3, t.getPerson().getId());
			pstmt.setInt(4, t.getBicycle().getId());
			
			pstmt.executeLargeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				o = t;
				o.setId(rs.getInt(1));
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
	public Offer getById(Integer id) {
		Offer o = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.offer_id, " + 
					"offer.bicycle_id, " + 
					"offer.person_id, " + 
					"offer.amount, " + 
					"offer.status_id, " + 
					"status.status_name " + 
					"from offer " + 
					"join status " + 
					"on offer.status_id = status.status_id " +
					"where offer.offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				
				o.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				o.setPerson(personDAO.getById(rs.getInt("person_id")));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				o.setStatus(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.offer_id, " + 
					"offer.bicycle_id, " + 
					"offer.person_id, " + 
					"offer.amount, " + 
					"offer.status_id, " + 
					"status.status_name " + 
					"from offer " + 
					"join status " + 
					"on offer.status_id = status.status_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				
				o.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				o.setPerson(personDAO.getById(rs.getInt("person_id")));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				o.setStatus(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public void update(Offer t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update offer set amount = ?, "
					+ "status_id = ?, "
					+ "bicycle_id = ?, "
					+ "person_id = ? "
					+ "where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, t.getAmount());
			pstmt.setInt(2, t.getStatus().getId());
			pstmt.setInt(3, t.getBicycle().getId());
			pstmt.setInt(4, t.getPerson().getId());
			pstmt.setInt(5, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
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

	@Override
	public void delete(Offer t) {
		try(Connection conn = cu.getConnection()){
			String sql = "delete from bicycle_offer where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			
			sql = "delete from person_offer where offer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Set<Offer> getOffersByBicycle(Bicycle b) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.offer_id, " + 
					"offer.bicycle_id, " + 
					"offer.person_id, " + 
					"offer.amount, " + 
					"offer.status_id, " + 
					"status.status_name " + 
					"from offer " + 
					"join status " + 
					"on offer.status_id = status.status_id " + 
					"where offer.bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				
				o.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				o.setPerson(personDAO.getById(rs.getInt("person_id")));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				o.setStatus(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public Set<Offer> getPendingBicycleOffers(Bicycle b) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.offer_id, " + 
					"offer.bicycle_id, " + 
					"offer.person_id, " + 
					"offer.amount, " + 
					"offer.status_id, " + 
					"status.status_name " + 
					"from offer " + 
					"join status " + 
					"on offer.status_id = status.status_id " +
					"where offer.status_id = 3 and bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				
				o.setBicycle(b);
				
				o.setPerson(personDAO.getById(rs.getInt("person_id")));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				o.setStatus(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public Set<Offer> getOffersByPerson(Person p) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.offer_id, " + 
					"offer.amount, " + 
					"offer.status_id, " + 
					"status.status_name, " + 
					"bicycle_offer.bicycle_id, " + 
					"person_offer.person_id " + 
					"from offer " + 
					"join bicycle_offer " + 
					"on offer.offer_id = bicycle_offer.offer_id " + 
					"join status " + 
					"on offer.status_id = status.status_id " + 
					"join person_offer " + 
					"on offer.offer_id = person_offer.offer_id "
					+ "where person_offer.person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				o.setAmount(rs.getDouble("amount"));
				
				o.setBicycle(bicycleDAO.getById(rs.getInt("bicycle_id")));
				
				o.setPerson(personDAO.getById(rs.getInt("person_id")));
				
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				o.setStatus(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public void updateOwner(Offer o, Person p) {
		// TODO Auto-generated method stub
		
	}

}
