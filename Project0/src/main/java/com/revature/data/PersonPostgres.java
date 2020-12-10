package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();
	
	@Override
	public Person getById(Integer id) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select person.id, username, user_role.id as role_id, role_name \r\n"
					+ "from person join user_role on person.role_id = user_role.id  where person.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				Person retPerson = new Person();
				retPerson.setId(rs.getInt("id"));
				retPerson.setUsername(rs.getString("username"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				retPerson.setRole(role);
				retPerson.setBicycles(bicyclePostgres.getOwnedBicycles(retPerson));
				person = retPerson;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> persons = new HashSet<Person>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select person.id, username, pwd, user_role.id as role_id, role_name \r\n"
					+ "from person join user_role on person.role_id = user_role.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while (rs.next()) {
				Person retPerson = new Person();
				retPerson.setId(rs.getInt("id"));
				retPerson.setUsername(rs.getString("username"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				retPerson.setRole(role);
				retPerson.setBicycles(bicyclePostgres.getOwnedBicycles(retPerson));
				persons.add(retPerson);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persons;
	}

	@Override
	public void update(Person p) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update person set username = ?, role_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, p.getUsername());
			pstmt.setInt(2, p.getRole().getId());
			pstmt.setInt(3, p.getId());
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
	public void delete(Person p) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sqlString = "delete from person where id = ?";
			String[] keyStrings = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keyStrings);
			pstmt.setInt(1, p.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resetDefault();
	}

	@Override
	public Person add(Person p) throws NonUniqueUsernameException {
		Person retPerson = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keys);
			pstmt.setString(1, p.getUsername());
			pstmt.setNull(2, java.sql.Types.NULL);
			pstmt.setInt(3, p.getRole().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				retPerson = p;
				retPerson.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}	
		} catch (Exception e) {
			if (e.getMessage().contains("violate unique constaint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		return retPerson;
	}

	@Override
	public Person getByUsername(String username) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select person.id, username, pwd, user_role.id as role_id, role_name \r\n"
					+ "from person join user_role on person.role_id = user_role.id  where person.username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, username);
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				Person retPerson = new Person();
				retPerson.setId(rs.getInt("id"));
				retPerson.setUsername(rs.getString("username"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				retPerson.setRole(role);
				retPerson.setBicycles(bicyclePostgres.getOwnedBicycles(retPerson));
				person = retPerson;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public void updatePassword(Person p, String newPassword) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update person set pwd = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, p.getId());
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
	public String getPassword(Person p) {
		String passwordString = "";
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select pwd from person where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, p.getId());
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.getResultSet();
			
			while (rs.next()) {
				passwordString = rs.getString("pwd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return passwordString;
	}
	
	public void resetDefault() {
		try (Connection conn = cu.getConnection()){
			String sqlString = "SELECT setval('bicycleshop.person_id_seq', max(id)) FROM person;";
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
