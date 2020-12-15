package dev.warrington.data;

import dev.warrington.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.warrington.beans.Role;
import dev.warrington.exceptions.NonUniqueUsernameException;
import dev.warrington.beans.Person;

public class PersonPostgres implements PersonDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person getByUsername(String username) {
		
		Person person = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select * from person join role on role.role_id = person.role_id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				person= new Person();
				person.setUsername(rs.getString("username"));
				person.setId(rs.getInt("person_id"));
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
				person.setPassHash(rs.getString("pass_hash"));
				person.setSalt(rs.getString("salt"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				person.setRole(job);
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Person add(Person p) throws NonUniqueUsernameException {

		Person person = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, 1, ?, ?, ?, ?, ?)";
			String[] keys = {"person_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getPassHash());
			pstmt.setString(3, p.getFirstName());
			pstmt.setString(4, p.getLastName());
			pstmt.setString(5, p.getSalt());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				person = p;
				person.setId(rs.getInt(1));
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
		
		try (Connection conn = cu.getConnection()) {
			
			String sql = "insert into author values (" + p.getId() + ", 100)";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			
		}  catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	public void delete(Integer id) throws NonUniqueUsernameException {
		
		try (Connection conn = cu.getConnection()) {
			
			String sql = "delete from author where author_id = " + id;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			
		}  catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		try (Connection conn = cu.getConnection()) {
			
			String sql = "delete from person where person_id = " + id;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}