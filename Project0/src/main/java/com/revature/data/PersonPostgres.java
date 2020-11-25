package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO{
	private ConnectionUtil cu;
	
	{
		cu = ConnectionUtil.getConnectionUtil();
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
//			conn.setAutoCommit(false);
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
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Person getById(Integer id) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id as person_id, role_id, role.name, username, password, "
					+ "from person join role on role_id = role.id "
					+ "where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("person.id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("password"));
				Role role = new Role();
				role.setId(rs.getInt("role.id"));
				role.setName(rs.getString("role.name"));
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
			String sql = "select person.id, role.id, role.name, username, password"
					+ "from person join role on user.role_id = role.id";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("person.id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("password"));
				Role job = new Role();
				job.setId(rs.getInt("role.id"));
				job.setName(rs.getString("role.name"));
				person.setRole(job);
				
				person.setBikes(getBikesByPersonId(person.getId(), conn));
				
				people.add(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
	}

	@Override
	public Person getByUsername(String username) {
		Person person = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select person.id, person.role_id, role.id, role.name, username, password, "
					+ "from person join role on person.role_id = role.id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()){
				person = new Person();
				person.setUsername(rs.getString("username"));
				person.setId(rs.getInt("person.id"));
				person.setPassword(rs.getString("password"));
				
				Role job = new Role();
				job.setId(rs.getInt("role.id"));
				job.setName(rs.getString("role_name"));
				person.setRole(job);
				
				person.setBikes(getBikesByPersonId(person.getId(), conn));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Person update(Person t) throws NonUniqueUsernameException {
		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
//			conn.setAutoCommit(false);
			String sql = "update person set username = ?, password = ?, role_id = ?) where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			pstmt.setInt(4, t.getId());
			
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
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public void delete(Person t) {
		try (Connection conn =  cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update bike set owner_id = null where owner_id = ?;"
						+ "update offer set person_id = null where person_id = ?;"
						+ "delete from person where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getId());
			pstmt.executeUpdate();
			
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private Set<Bike> getBikesByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Bike> bikes = new HashSet<>();
		
		String sql = "select model, brand, color, owner_id, bike.id, user.id, bike.owner_id from bike join person on bike.owner_id = person.id where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike bike = new Bike();
			bike.setId(rs.getInt("bike.id"));
			bike.setBrand(rs.getString("brand"));
			bike.setModel(rs.getString("model"));
			bike.setColor(rs.getString("color"));
			
			Person owner = this.getById(rs.getInt("owner_id"));
			bike.setOwner(owner);
			bikes.add(bike);
		}
		
		return bikes;
	}

}
