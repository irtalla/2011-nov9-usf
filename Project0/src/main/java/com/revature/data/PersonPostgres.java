package com.revature.data;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person getById(Integer id) {
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.person_id, " + 
					"person.uzername, " + 
					"person.pass, " + 
					"person.role_id, " + 
					"role.role_name " + 
					"from person " + 
					"join role " + 
					"on person.role_id = role.role_id " + 
					"where person.person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Person();
				p.setId(rs.getInt("person_id"));
				p.setUsername(rs.getString("uzername"));
				p.setPassword(rs.getString("pass"));
				
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> persons = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.person_id, " + 
					"person.uzername, " + 
					"person.pass, " + 
					"person.role_id, " + 
					"role.role_name " + 
					"from person " + 
					"join role " + 
					"on person.role_id = role.role_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("person_id"));
				p.setUsername(rs.getString("uzername"));
				p.setPassword(rs.getString("pass"));
				
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public void update(Person t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update person set uzername = ?, pass = ?, role_id = ? where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			pstmt.setInt(4, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Person t) {
		try(Connection conn = cu.getConnection()){
			// We need to delete the person's offers
			String sql = "delete from offer where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			
			// now we delete the person
			sql = "delete from person where person_id = ?";
			pstmt.setInt(2, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"person_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.executeLargeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Person getByUsername(String username) {
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.person_id, " + 
					"person.uzername, " + 
					"person.pass, " + 
					"person.role_id, " + 
					"role.role_name " + 
					"from person " + 
					"join role " + 
					"on person.role_id = role.role_id " + 
					"where person.uzername = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Person();
				p.setId(rs.getInt("person_id"));
				p.setUsername(rs.getString("uzername"));
				p.setPassword(rs.getString("pass"));
				
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
