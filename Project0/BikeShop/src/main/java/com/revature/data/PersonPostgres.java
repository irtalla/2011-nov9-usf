package com.revature.data;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"person_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				Person p = new Person(t);
				p.setId(rs.getInt(1));
				conn.commit();
				return p;
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Person getById(Integer id) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = 
//					"select person.id as person_id, user_role.id as role_id, username, passwd, "
//					+ "user_role.name as role_name from person join user_role on user_role_id = user_role.id "
//					+ "where person_id = ?";
					"select person.person_id, user_role.user_role_id, person.username, person.passwd, "
					+ "user_role.name from person join user_role " 
					+ "on user_role.user_role_id = person.user_role_id where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("person_id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("passwd"));
				Role role = new Role();
				role.setId(rs.getInt("user_role_id"));
				role.setName(rs.getString("name"));
				person.setRole(role);
				
				person.setBikes(getBikesByPersonId(person.getId(), conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person join user_role on user_role_id = user_role.id";
			
			/* Add casts to following statements
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(sql);
			*/
			
			Statement state = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) state).executeQuery(sql);
			
			while (rs.next()) {
				Person human = new Person();
				human.setId(rs.getInt("person_id"));
				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				
				human.setBikes(getBikesByPersonId(human.getId(), conn));
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
	}

	@Override
	public Person getByUsername(String username) {
		Person human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select "
					+ "p.person_id as person_id, ur.user_role_id as role_id, "
					+ "p.username as username, p.passwd as passwd, "
					+ "ur.name as role_name "
					+ "from person as p "
					+ "join user_role as ur on ur.user_role_id = p.user_role_id "
					+ "where p.username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new Person();
				human.setUsername(rs.getString("username"));
				human.setId(rs.getInt("person_id"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				human.setBikes(getBikesByPersonId(human.getId(), conn));
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		try (Connection conn =  cu.getConnection())
		{
			// added this
			conn.setAutoCommit(false);
			String sql = "delete from person_bike where person_id = ?; delete from person where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Set<Bike> getBikesByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		BikeDAO bikeDao = new BikePostgres();
		
		String sql = "select * from person_bike where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike hobby = bikeDao.getById(rs.getInt("bike_id"));
			bikes.add(hobby);
		}
		
		return bikes;
	}

}
