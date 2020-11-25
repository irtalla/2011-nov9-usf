package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Offer;
import com.revature.beans.Offer;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer t) throws Exception {
		Offer o = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?)";
			String[] keys = {"id", "person_id", "offer_id", "weekly_payment", "weeks"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getPerson().getId());
			pstmt.setInt(2, t.getBike().getId());
			pstmt.setDouble(3, t.getWeeklyPayment());
			pstmt.setInt(4, t.getWeeks());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				o = t;
				o.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public Offer getById(Integer id) {
		Offer match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select person_id, offer_id, weekly_payment, weeks, status.id, status.name "
						+ "from offer join status on offer.status_id = status.id where offer.id = ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Offer();
				match.setId(rs.getInt("id"));
				match.setWeeklyPayment(rs.getDouble("weekly_payment"));
				match.setWeeks(rs.getInt("weeks"));
				
				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
				match.setPerson(offerer);
				
				Bike bike = new BikePostgres().getById(rs.getInt("offer.id"));
				match.setBike(bike);
				
				Status status = this.getStatusById(rs.getInt("status_id"));
				match.setStatus(status);
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return match;
	}

	private Status getStatusById(Integer id) {
		Status match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select * from status where id = ? ";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Status();
				match.setId(rs.getInt("id"));
				match.setName(rs.getString("name"));
				
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}
	
	private Status getStatusByName(String name) {
		Status match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select * from status where name = ? ";
			String[] keys = {"name"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Status();
				match.setId(rs.getInt("id"));
				match.setName(rs.getString("name"));
				
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person_id, offer_id, weekly_payment, weeks, status.id, status.name "
					+ "from offer join status on offer.status_id = status.id where offer.id = ?)";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
				
				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				Bike bike = new BikePostgres().getById(rs.getInt("offer.id"));
				offer.setBike(bike);
				
				Status status = this.getStatusById(rs.getInt("status_id"));offer.setStatus(status);
				offers.add(offer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public void delete(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from offer where id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Offer update(Offer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffersMadeByPerson(Person t) {
		Set<Offer> activeOffers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select person_id, bike_id, weekly_payment, weeks, status.id, status.name "
						+"from offer join status on status.id = offer.status_id where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
				Status status = this.getStatusById(rs.getInt("status_id"));
				offer.setStatus(status);
				
				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				if(status.getName().equals("Active")) {
					activeOffers.add(offer);
				}
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeOffers;
	}

	@Override
	public Set<Offer> getActiveOffersForBike(Bike t) {
		Set<Offer> activeOffers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select person_id, bike_id, weekly_payment, weeks, status.id, status.name "
						+"from offer join status on status.id = offer.status_id where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
				Status status = this.getStatusById(rs.getInt("status_id"));
				offer.setStatus(status);
				
				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				if(status.getName().equals("Active")) {
					activeOffers.add(offer);
				}
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeOffers;
	}

	@Override
	public void acceptOfferForBike(Bike b, Offer o) {
		//update status of bike
		Status accepted = this.getStatusByName("Accepted");
		
		Offer updatedOffer = o;
		updatedOffer.setStatus(accepted);
		
		updatedOffer = this.update(updatedOffer);
		
		Person owner = updatedOffer.getPerson();
		
		Bike updatedBike = b;
		updatedBike.setOwner(owner);
		//updatedBike.setAcceptedOffer(updatedOffer);
		updatedBike = new BikePostgres().update(updatedBike);
		
		for(Offer offer : updatedBike.getOffers()) {
			if(offer.getId() != o.getId()) {
				this.delete(offer);
			}
		}
	}

	@Override
	public void rejectOfferForBike(Bike b, Offer o) {
		Status rejected = this.getStatusByName("Rejected");
		
		Offer updatedOffer = o;
		updatedOffer.setStatus(rejected);
		
		updatedOffer = this.update(updatedOffer);
	}
}
