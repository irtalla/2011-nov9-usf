package data;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import java.sql.SQLException;
import java.sql.Statement;

import beans.Offers;
import beans.Bikes;
import beans.Role;
import beans.Usr;
import exceptions.NonUniqueUsernameException;
import utilities.ConnectionUtil;

public class UserPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Usr add(Usr t) throws NonUniqueUsernameException {
		Usr u = null;
	
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into usr values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				u = t;
				u.setID(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
	} catch (Exception e) {
		if (e.getMessage().contains("violates unique constraint")) {
			throw new NonUniqueUsernameException();
		}
		e.printStackTrace();
	}
		return u;
	}
@Override
public Set<Usr> getAll() {
	Set<Usr> usrs = new HashSet<>();
	
	try (Connection conn = cu.getConnection()) {
		String sql = "select * from usr";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Usr usr = new Usr();
			usr.setID(rs.getInt("id"));
			usr.setUsername(rs.getString("name"));
			usr.setPassword(rs.getString("passwd"));
			Integer role_id = rs.getInt("usr_role_id");
			Role r = new Role();
			if(role_id == 1) {
				r.setName("User");
				r.setId(1);
			}
			else { r.setName("Employee");
			r.setId(2);
			}
			usr.setRole(r);
			usrs.add(usr);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return usrs;
}
@Override
public Usr getById(Integer id) {
	Usr user = null;
	
	try (Connection conn = cu.getConnection()) {
		String sql = "select usr.id, usr_role.id as usr_role_id, username, passwd, "
				+ "usr_role.name from usr join usr_role on usr.usr_role_id = usr_role.id" +
				" where usr.id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			user = new Usr();
			user.setID(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("passwd"));
			Role role = new Role();
			role.setId(rs.getInt("usr_role_id"));
			role.setName(rs.getString("name"));
			user.setRole(role);
			user.setBikes(getBikesByUserId(user.getId()));
		}
	}	catch (Exception e) {
		e.printStackTrace();
	}
	return user;
}
@Override
public void update(Usr t) {
	// TODO Auto-generated method stub
	
}
@Override
public void delete(Usr t) {
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);
		String sql = "delete from usr where usr.id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, t.getId());
		int rowsAffected = pstmt.executeUpdate();
		
		sql = "delete from usr_bike where usr_id = ?";
		pstmt.setInt(1, t.getId());
		
		rowsAffected = pstmt.executeUpdate();
		if(rowsAffected > 0)
			conn.commit();
		else
			conn.rollback();
} catch (Exception e) {
	e.printStackTrace();}
}
@Override
public Usr getByUsername(String username) {
	Usr user = null;
	
	try (Connection conn = cu.getConnection())
			{
				String sql = "select usr.id as id, usr.usr_role_id, usr.username, usr.passwd, usr_role.name, usr.money from usr full join usr_role on usr_role.id = usr_role_id where username = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					user = new Usr();
					user.setID(rs.getInt("id"));
					user.setPassword(rs.getString("passwd"));
					user.setMoney(rs.getDouble("money"));
					Role job = new Role();
					job.setId(rs.getInt("usr_role_id"));
					job.setName(rs.getString("name"));
					user.setRole(job);
					user.setBikes(getBikesByUserId(user.getId()));
				}
			}
	catch (Exception e) {
		e.printStackTrace();
	}
	return user;
}

public Set<Bikes> getBikesByUserId(Integer id) {
	Set<Bikes> bikes = new  HashSet<>();
	try (Connection conn = cu.getConnection())
	{
	BikeDAO bikeDao = new BikePostgres();
	
	String sql = "select * from usr_bike"
			+ " where usr_id = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, id);
	ResultSet rs = pstmt.executeQuery();
	
	while (rs.next()) {
		Bikes bike = bikeDao.getById(rs.getInt("bike_id"));
		bikes.add(bike);
	}
} catch (Exception e) {
	e.printStackTrace();
} return bikes;
}
@Override
public void placeOffer(Offers offer) throws SQLException {
	
	try (Connection conn = cu.getConnection()) {
		
		conn.setAutoCommit(false);
		String sql = "insert into offers values (default, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, offer.getOfferer());
		pstmt.setDouble(2, offer.getAmount());
		pstmt.setInt(3, offer.getTargetBike().getId());
		pstmt.setString(4, "Pending");
		
		int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
	} catch (Exception e) {
		e.printStackTrace();
	}

}
@Override
public Set<Offers> getOffers() throws SQLException {
	BikeDAO bikeDao = new BikePostgres();
	Set<Offers> offers = new HashSet<>();
	try (Connection conn = cu.getConnection()) {
		String sql = "select offers.target_bike, offers.offer_number, offers.offerer, offers.offer_amount, offers.accept_status from offers";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Offers offer = new Offers();
			offer.setID(rs.getInt("offer_number"));
			offer.setAmount(rs.getDouble("offer_amount"));
			offer.setOfferer(rs.getInt("offerer"));
			offer.setTargetBike(bikeDao.getById(rs.getInt("target_bike")));
			offer.setStatus(rs.getString("accept_status"));
			offers.add(offer);
		}
		} catch (Exception e) {
			e.printStackTrace();
	}
	return offers;
}
@Override
public Offers getOfferById(Integer id) throws SQLException {
	BikeDAO bikeDao = new BikePostgres();
	Offers offer = new Offers();
	try (Connection conn = cu.getConnection()) {
		String sql = "select * from offers where offer_number = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, offer.getId());
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			offer = new Offers();
			offer.setID(rs.getInt("offer_number"));
			offer.setOfferer(rs.getInt("offerer"));
			offer.setAmount(rs.getDouble("amount"));
			offer.setTargetBike(bikeDao.getById(rs.getInt("target_bike")));
			offer.setStatus(rs.getString("accept_status"));
		}
	} catch (Exception e) {
		e.printStackTrace();
}
	return offer;
}
@Override
public void accept(Integer id) {
	try (Connection conn = cu.getConnection()) {
		Offers offer = null;
		UserDAO userDao = new UserPostgres();
		BikeDAO bikeDao = new BikePostgres();
		conn.setAutoCommit(false);
		String sql = "select * from offers where offer_number = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			offer = new Offers();
			offer.setID(rs.getInt("offer_number"));
			offer.setOfferer(rs.getInt("offerer"));
			offer.setTargetBike(bikeDao.getById(rs.getInt("target_bike")));
			offer.setStatus("Accepted");
		}
		conn.commit();
		sql = "update offers set accept_status = 'Accepted' where offerer = ? AND offer_number = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  offer.getOfferer());
		pstmt.setInt(2,  id);
		pstmt.executeUpdate();
		int offerid = offer.getOfferer();
		int bikeid = offer.getTargetBike().getId();
		conn.commit();
			try {
			sql = "delete from offers where target_bike = ? AND offerer != ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bikeid);
			pstmt.setInt(2, offerid);
			pstmt.executeUpdate();
			conn.commit(); }
				catch (Exception e) {
				e.printStackTrace();
				}
			sql = "update bike set status_name = 'Unavailable' where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bikeid);
			pstmt.executeUpdate();
			conn.commit();
			sql = "insert into usr_bike values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offer.getOfferer());
			pstmt.setInt(2, offer.getTargetBike().getId());
			pstmt.executeUpdate();
			conn.commit();
	} catch (Exception e) {
		e.printStackTrace();
	} 
}
@Override
public Set<Bikes> getBikesByUsername(String username) {

	// no need to freakin care
	return null;
}
@Override
public double getMoney(Usr u) {
	Double money = 0.00;
	try(Connection conn = cu.getConnection()) {
	String sql = "select money from usr where id = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, u.getId());
	ResultSet rs = pstmt.executeQuery(); 
	
	if (rs.next()) {
		money = rs.getDouble("money");
		u.setMoney(money);
	}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return money;
}
@Override
public void setMoney(double money, Usr u) {
	try(Connection conn = cu.getConnection()) {
	String sql ="update usr set money = ? where id = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setDouble(1, money);
	pstmt.setInt(2, u.getId());
	pstmt.executeUpdate();
} catch (Exception e) {
	e.printStackTrace();
}
}
}
