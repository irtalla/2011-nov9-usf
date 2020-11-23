package dev.elliman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;
import dev.elliman.utils.DBConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private DBConnectionUtil cu = DBConnectionUtil.getDBConnectionUtil();
	private PersonDAO personDAO = new PersonDAOFactory().getPersonDAO();
	private BikeDAO bikeDAO = new BikeDAOFactory().getBikeDAO();

	@Override
	public Integer makeOffer(Offer offer) {
		Integer generatedID = null;
		
			try(Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "insert into offers values (default, ?, ?, ?, ?)";
				String[] keys = {"id"};
				PreparedStatement pstmt = conn.prepareStatement(sql, keys);
				pstmt.setInt(1, offer.getPerson().getID());
				pstmt.setInt(2, offer.getBike().getId());
				pstmt.setInt(3, offer.getPrice());
				pstmt.setString(4, offer.getStatus());
				
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					generatedID = rs.getInt(1);
					offer.setId(generatedID);
					conn.commit();
				} else {
					conn.rollback();
				}
				
			} catch (Exception e) {
				
			}
		
		return generatedID;
	}

	@Override
	public void update(Offer offer) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update offers set status = ? where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, offer.getStatus());
			pstmt.setInt(2, offer.getId());
			
			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rejectAll(Bike bike) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update offers set status = 'Rejected' where bike_id = ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bike.getId());
			
			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Offer> getOffers(Person person) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, person.getID());
			
			ResultSet rs = pstmt.executeQuery();
			
			Offer o;
			while(rs.next()) {
				o = new Offer(person, bikeDAO.getByID(rs.getInt("bike_id")), rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getActiveOffers(Person person) {
Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers where person_id = ? and status = 'Pending'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, person.getID());
			
			ResultSet rs = pstmt.executeQuery();
			
			Offer o;
			while(rs.next()) {
				o = new Offer(person, bikeDAO.getByID(rs.getInt("bike_id")), rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getOffers(Bike bike) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bike.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			Offer o;
			while(rs.next()) {
				o = new Offer(personDAO.getByID(rs.getInt("person_id")), bike, rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getActiveOffers(Bike bike) {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers where bike_id = ? and status = 'Pending'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bike.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			Offer o;
			while(rs.next()) {
				o = new Offer(personDAO.getByID(rs.getInt("person_id")), bike, rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getAllOffers() {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers";
			Statement pstmt = conn.createStatement();
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			Offer o;
			while(rs.next()) {
				o = new Offer(personDAO.getByID(rs.getInt("person_id")), bikeDAO.getByID(rs.getInt("bike_id")), rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Set<Offer> getAllActiveOffers() {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from offers where status = 'Pending'";
			Statement pstmt = conn.createStatement();
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			Offer o;
			while(rs.next()) {
				o = new Offer(personDAO.getByID(rs.getInt("person_id")), bikeDAO.getByID(rs.getInt("bike_id")), rs.getInt("price"));
				offers.add(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Offer getById(Integer id) {
		Offer offer = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from offers where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				offer = new Offer(
						personDAO.getByID(rs.getInt("person_id")),
						bikeDAO.getByID(rs.getInt("bike_id")),
						rs.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offer;
	}

}
