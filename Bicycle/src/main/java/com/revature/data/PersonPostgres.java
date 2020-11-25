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
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
	Person p = null;
	
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);
		String sql = "insert into person values (default, ?, ?, ?)";
		String[] keys = {"id"};
		PreparedStatement pstmt = conn.prepareStatement(sql, keys);
		pstmt.setString(1, t.getUsername());
		pstmt.setString(2, t.getPassword());
		pstmt.setInt(3, t.getRole().getId());

		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		if (rs.next()) {
			p = t;
			p.setId(rs.getInt(1));
			conn.commit();
		} else {
			conn.rollback();
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return p;
	}
	@Override
	public Person getById(Integer id) {


		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
		
			String sql = "select * from person where id =?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				{
					p = new Person();
					p.setUsername(rs.getString("username"));
					p.setId(rs.getInt("id"));
					p.setPassword(rs.getString("passwd"));
					Role job = new Role();
					job.setId(rs.getInt("id"));
					if (job.getId()==1) job.setName("employee");
					else if (job.getId()==2) job.setName("user");
					else job.setName("manager");
					p.setRole(job);
					p.setBikes(getBikesByPersonId(p.getId(), conn)); // retrieve bikes for specific owner
				}	

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
	}
		return p;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> persons = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "SELECT * from person";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("user_role_id"));
				if (r.getId()==1) r.setName("employee");
				else if (r.getId()==2) r.setName("user");
				else r.setName("manager");
				person.setRole(r);
				person.setBikes(getBikesByPersonId(person.getId(), conn)); // retrieve bikes for specific owner
				persons.add(person);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persons;
		
	}
	

   @Override
    public Person getByUsername(String username) {
	Person p = null;
	
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);


		
		String sql = "select person.id, user_role.id, username, passwd, "
				+ "user_role.user_role_name from person "
				+ "join user_role on user_role_id = user_role.id where username = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			System.out.println("There is no user with that username/password combination.");
		    return null;	
		}
		
		if (rs.next())
			{
				p = new Person();
				p.setUsername(rs.getString("username"));
				p.setId(rs.getInt("id"));
				p.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("id"));
				job.setName(rs.getString("user_role_name"));
				p.setRole(job);
				p.setBikes(getBikesByPersonId(p.getId(), conn)); // retrieve bikes for specific owner
			}		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return p;
   }		
	
	
	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		Offer o = null;
		Offer offer = new Offer();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from person where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		
	}
	

	}
	private Set<Bike> getBikesByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		BikeDAO bikeDao = new BikePostgres();
		
		String sql = "select * from bike where owner_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			 Bike b = bikeDao.getById(rs.getInt("id"));  /// change this from bike_id
			bikes.add(b);
		}
		
		return bikes;
	}
}
