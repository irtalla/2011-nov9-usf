package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "insert into offer values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
//			String[] keys = {"id", "person_id", "bike_id", "status_id", "weekly_payment", "weeks"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getBike().getId());
			pstmt.setInt(2, t.getPerson().getId());
			pstmt.setDouble(3, t.getWeeklyPayment());
			pstmt.setInt(4, t.getWeeks());
			pstmt.setInt(5, 2); //status_id for a new offer is by default 2
			
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
			String sql = "select person_id, bike_id, weekly_payment, weeks, status_id, status.name as status_name "
						+ "from offer join status on status_id = status.id where offer.id = ?;";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				match = new Offer();
				match.setId(id);
				match.setWeeklyPayment(rs.getDouble("weekly_payment"));
				match.setWeeks(rs.getInt("weeks"));
				
				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				match.setPerson(offerer);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				match.setBike(bike);
				
//				Status status = this.getStatusById(rs.getInt("status_id"));
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setName(rs.getString("status_name"));
				match.setStatus(status);
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
		//status does not count as a relation
		Set<Offer> offers = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.id as offer_id, person_id, bike_id, weeks, weekly_payment "
					+ " status_id, status.name as status_name from offer join status on status_id = status.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("offer_id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
				
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setName(rs.getString("status_name"));
				offer.setStatus(status);
				
				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				offer.setBike(bike);
				
				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
		
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select person_id, id, bike_id, weekly_payment, weeks, status_id, status.name as status_name"
//					+ "from offer join status on status_id = status.id;";
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			while (rs.next()) {
//				Offer offer = new Offer();
//				offer.setId(rs.getInt("id"));
//				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
//				offer.setWeeks(rs.getInt("weeks"));
//				
//				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
//				offer.setPerson(offerer);
//				
//				Bike bike = new BikePostgres().getById(rs.getInt("offer.id"));
//				offer.setBike(bike);
//				
//				Status status = this.getStatusById(rs.getInt("status_id"));offer.setStatus(status);
//				offers.add(offer);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return offers;
	}

	@Override
	public Set<Offer> getAllActiveOffers() {
		Set<Offer> offers = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from offer where status_id = 2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
				
				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				offer.setBike(bike);
				
				offers.add(offer);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select person_id, offer.id as offer_id, bike_id, weekly_payment, weeks "
//					+ "from offer where status_id = 2;";
//			String sql = "select person_id, username, password, offer.id as offer_id, bike_id, weekly_payment, weeks "
//					+ "from offer join person on person_id = person.id where person.id IS null;";
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			while (rs.next()) {
//				Offer offer = new Offer();
//				offer.setId(rs.getInt("offer_id"));
//				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
//				offer.setWeeks(rs.getInt("weeks"));
//				
//				Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
//				Person offerer = new Person();
//				offerer.setId(rs.getInt("person_id"));
//				offerer.setUsername(rs.getString("username"));
//				offerer.setUsername(rs.getString("password"));
//				offer.setPerson(offerer);
//				
//				Bike bike = new BikePostgres().getById(rs.getInt("bike_id"));
//				offer.setBike(bike);
//				
////				Status status = this.getStatusById(rs.getInt("status_id"));
//				Status status = new Status();
//				status.setId(2);
//				status.setName("Active");
//				offer.setStatus(status);
//				offers.add(offer);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return offers;
	}
	
	private Set<Offer> getAllActiveOffersWithoutRelations() {
		Set<Offer> offers = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from offer where status_id = 2;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
				
				Status status = new Status();
				status.setId(2);
				status.setName("Active");
				offer.setStatus(status);
				
				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	private Set<Offer> getAllOffersWithoutRelations() {
		//status does not count as a relation
		Set<Offer> offers = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select offer.id as offer_id, person_id, bike_id, weeks, weekly_payment "
					+ " status_id, status.name as status_name from offer join status on status_id = status.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("offer_id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
				
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setName(rs.getString("status_name"));
				offer.setStatus(status);
				
				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public Offer acceptOffer(Offer t) {
		Person offerer = t.getPerson();
		Bike bike = t.getBike();
		bike.setOwner(offerer);
		//update bike in db with owner
		Bike updatedBike = new BikePostgres().update(bike);
		
		Status accepted = new Status();
		accepted.setId(1);
		accepted.setName("Accepted");
		
		Offer updatedOffer = t;
		updatedOffer.setStatus(accepted);
		updatedOffer = this.update(updatedOffer);
		
		Set<Offer> allOtherOffers = this.getActiveOffersForBike(updatedBike);
		for(Offer offer : allOtherOffers) {
			Status rejected = new Status();
			rejected.setId(3);
			rejected.setName("Rejected");
			offer.setStatus(rejected);
			
			this.update(offer);
		}
		
		return updatedOffer;
	}
	
	@Override
	public Offer rejectOffer(Offer t) {
		Status rejected = new Status();
		rejected.setId(3);
		rejected.setName("Rejected");
		
		Offer updatedOffer = t;
		updatedOffer.setStatus(rejected);
		
		return this.update(updatedOffer);
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
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			//only every modifying the status of an offer... for now
			String sql = "update offer set status_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getStatus().getId());
			pstmt.setInt(2, t.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			
			return this.getById(t.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Offer> getActiveOffersMadeByPerson(Person t) {
		Set<Offer> activeOffers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer where person_id = ? AND status_id = 2";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
//				Status status = this.getStatusById(rs.getInt("status_id"));
				Status status = new Status();
				status.setId(2);
				status.setName("Active");
				offer.setStatus(status);
				
//				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				offer.setPerson(t);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				offer.setBike(bike);
				
				activeOffers.add(offer);
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
//			String sql = "select person_id, bike_id, weekly_payment, weeks, "
//					+ "status.id as status_id, status.name as status_name "
//					+ "from offer join status on status.id = offer.status_id where bike_id = ?";
			String sql = "select * from offer where bike_id = ? AND status_id = 2";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
				Status status = new Status();
				status.setId(2);
				status.setName("Active");
				offer.setStatus(status);
				
				//I will only ever need shallow info about person when this method is invoked:
				//Person offerer = new PersonPostgres().getById(rs.getInt("person_id"));
				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				offer.setBike(bike);
				
				activeOffers.add(offer);
//				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeOffers;
	}
	
	@Override
	public Offer getAcceptedOfferForBike(Bike t) {
		Offer offer = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer where bike_id = ? AND status_id = 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
				Status status = new Status();
				status.setId(1);
				status.setName("Accepted");
				offer.setStatus(status);
				
				Person offerer = new PersonPostgres().getByIdWithoutRelations(rs.getInt("person_id"));
				offer.setPerson(offerer);
				
				Bike bike = new BikePostgres().getByIdWithoutRelations(rs.getInt("bike_id"));
				offer.setBike(bike);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offer;
	}

	public Set<Offer> getActiveOffersForBikeWithIdWithoutRelations(Integer id) {
		//status does not count as a relation
		Set<Offer> activeOffers = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer where bike_id = ? AND status_id = 2";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setWeeklyPayment(rs.getDouble("weekly_payment"));
				offer.setWeeks(rs.getInt("weeks"));
					
				Status status = new Status();
				status.setId(2);
				status.setName("Active");
				offer.setStatus(status);
				
				activeOffers.add(offer);
//				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeOffers;
	}
}
