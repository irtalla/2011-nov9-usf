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
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bike add(Bike t) {
		Bike c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getBrand());
			pstmt.setString(2, t.getModel());
			pstmt.setString(3, t.getColor());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				c = t;
				c.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Bike getById(Integer id) {
		Bike match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select * from bike where bike.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
//			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Bike();
				match.setId(rs.getInt("id"));
				match.setBrand(rs.getString("brand"));
				match.setModel(rs.getString("model"));
				match.setColor(rs.getString("color"));	
				
				Person owner = new PersonPostgres().getByIdWithoutRelations(rs.getInt("owner_id"));
				match.setOwner(owner);
				
				Set<Offer> offers = new OfferPostgres().getActiveOffersForBikeWithIdWithoutRelations(id);
				match.setOffers(offers);
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return match;
	}
	
	public Bike getByIdWithoutRelations(Integer id) {
		Bike match = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select * from bike where bike.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
//			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Bike();
				match.setId(rs.getInt("id"));
				match.setBrand(rs.getString("brand"));
				match.setModel(rs.getString("model"));
				match.setColor(rs.getString("color"));	
				
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
			String sql = "select name from status where id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				match = new Status();
				match.setId(id);
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
	public Set<Bike> getAll() {
		Set<Bike> bikes = this.getAllWithoutRelations();
		for(Bike bike : bikes) {
			Person owner = new PersonPostgres().getByIdWithoutRelations(bike.getId());
			bike.setOwner(owner);
			
			Set<Offer> offers = new OfferPostgres().getActiveOffersForBikeWithIdWithoutRelations(bike.getId());
			bike.setOffers(offers);
		}
		
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select bike.id as bike_id, brand, model, color, "  
//						+ "person.id as person_id, username, password from bike left join person on bike.owner_id = person.id;";
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			while (rs.next()) {
//				Bike bike = new Bike();
//				bike.setId(rs.getInt("bike_id"));
//				bike.setModel(rs.getString("brand"));
//				bike.setModel(rs.getString("model"));
//				bike.setModel(rs.getString("color"));
//				
////				Person owner = new PersonPostgres().getById(rs.getInt("owner_id"));
//				Person owner = new Person();
//				owner.setId(rs.getInt("person_id"));
//				owner.setUsername(rs.getString("username"));
//				owner.setPassword(rs.getString("password"));
//				bike.setOwner(owner);
//				
//				Set<Offer> offers = new OfferPostgres().getActiveOffersForBike(bike);
//				bike.setOffers(offers);
//				
//				bikes.add(bike);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return bikes;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> allAvailable = new HashSet<>();

		//available bikes have a null owner_id:
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bike where owner_id IS null;";
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setBrand(rs.getString("brand"));
				bike.setModel(rs.getString("model"));
				bike.setColor(rs.getString("color"));
				
				Status status = new Status();
				status.setId(2);
				status.setName("Available");
				bike.setStatus(status);
				allAvailable.add(bike);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allAvailable;
	}

	@Override
	public Bike update(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			if(t.getOwner() == null) {
				String sql = "update bike set brand = ?, model = ?, color = ? where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getBrand());
				pstmt.setString(2, t.getModel());
				pstmt.setString(3, t.getColor());
				pstmt.setInt(4, t.getId());
				pstmt.executeUpdate();
			}else {
				String sql = "update bike set brand = ?, model = ?, color = ?, owner_id = ? where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getBrand());
				pstmt.setString(2, t.getModel());
				pstmt.setString(3, t.getColor());
				pstmt.setInt(4, t.getOwner().getId());
				pstmt.setInt(5, t.getId());
				pstmt.executeUpdate();
			}
			
			conn.commit();
				
			return this.getById(t.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bike where id = ?";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Set<Bike> getAllWithoutRelations() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bike;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Bike bike = this.getByIdWithoutRelations(id);
				if(bike == null) {
					System.out.println("Error! No bike with id " + id);
					break;
				}
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	public Set<Bike> getAllBikesOwnedByPersonWithId(Integer id) {
		Set<Bike> bikes = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike where owner_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setBrand(rs.getString("Brand"));
				bike.setModel(rs.getString("Model"));
				bike.setColor(rs.getString("Color"));
				
				Person owner = new PersonPostgres().getByIdWithoutRelations(rs.getInt("owner_id"));
				bike.setOwner(owner);
				
				Set<Offer> offers = new OfferPostgres().getActiveOffersForBikeWithIdWithoutRelations(rs.getInt("id"));
				bike.setOffers(offers);
				
				bikes.add(bike);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	@Override
	public Set<Bike> getUnavailableBikes() {
		Set<Bike> allUnavailable = new HashSet<>();

		//unavailable bikes have an owner_id:
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bike where owner_id IS NOT null;";
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setBrand(rs.getString("brand"));
				bike.setModel(rs.getString("model"));
				bike.setColor(rs.getString("color"));
				
				Status status = new Status();
				status.setId(1);
				status.setName("Accepted");
				bike.setStatus(status);
				allUnavailable.add(bike);
				
				//not necessary to get owner or accepted offer for now
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allUnavailable;
	}

}
