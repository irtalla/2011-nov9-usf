package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	BicyclePostgres bicyclePostgres = new BicyclePostgres();
	PersonPostgres personPostgres = new PersonPostgres();

	@Override
	public Offer add(Offer t) {
		Offer retValOffer = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "insert into offer values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString, keys);
			pstmt.setInt(1, t.getBicycle().getId());
			pstmt.setInt(2, t.getPerson().getId());
			pstmt.setDouble(3, t.getPrice());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				retValOffer = t;
				retValOffer.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return retValOffer;
	}

	@Override
	public Offer getById(Integer id) {
		Offer offer = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select id, bicycle_id, customer_id, amount from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				Bicycle bicycle = bicyclePostgres.getById(rs.getInt("bicycle_id"));
				Person person = personPostgres.getById(rs.getInt("customer_id"));
				Offer retOffer = new Offer(id, bicycle, person, rs.getDouble("amount"));
				offer = retOffer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offer;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<Offer>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select id, bicycle_id, customer_id, amount from offer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while(rs.next()) {
				Bicycle bicycle = bicyclePostgres.getById(rs.getInt("bicycle_id"));
				Person person = personPostgres.getById(rs.getInt("customer_id"));
				Integer id = rs.getInt("id");
				Offer retOffer = new Offer(id, bicycle, person, rs.getDouble("amount"));
				offers.add(retOffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()){
			String sqlString = "update offer set bicycle_id  = ?, customer_id  = ?, amount = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, t.getBicycle().getId());
			pstmt.setInt(2, t.getPerson().getId());
			pstmt.setDouble(3, t.getPrice());
			pstmt.setInt(4, t.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Offer t) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sqlString = "delete from offer where id = ?";
			String[] keyStrings = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keyStrings);
			pstmt.setInt(1, t.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Offer> getOfferByBicycle(Bicycle b) {
		Set<Offer> offers = new HashSet<Offer>();
		
		try(Connection conn = cu.getConnection()){
			String sqlString = "select id, customer_id, amount from offer where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, b.getId());
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				Person person = personPostgres.getById(rs.getInt("customer_id"));
				Double price = rs.getDouble("amount");
				Integer id = rs.getInt("id");
				Offer offer = new Offer(id, b, person, price);
				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public Set<Offer> getOfferByPerson(Person p) {
		Set<Offer> offers = new HashSet<Offer>();
		
		try(Connection conn = cu.getConnection()){
			String sqlString = "select id, bicycle_id, amount from offer where customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, p.getId());
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				Bicycle b = bicyclePostgres.getById(rs.getInt("bicycle_id"));
				Double price = rs.getDouble("amount");
				Integer id = rs.getInt("id");
				Offer offer = new Offer(id, b, p, price);
				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	
	public void resetDefault() {
		try (Connection conn = cu.getConnection()){
			String sqlString = "select setval('bicycleshop.offer_id_seq', max(id)) FROM offer";
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sqlString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
