package dev.warrington.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashSet;
import java.util.Set;

import dev.warrington.beans.Role;

import dev.warrington.beans.Person;
import dev.warrington.exceptions.NonUniqueUsernameException;
import dev.warrington.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person t) {
		
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from bike_shop_users where id = " + t.getId();
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		
		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike_shop_users values (default, ?, ?, ?)";
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
	public Person getByUsername(String username) {

		Person p = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select bike_shop_users.id as person_id, role.id as role_id, screen_name, password, "
					+ "role.name as role_name from bike_shop_users "
					+ "join role on bike_shop_users.role = role.id where screen_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				p = new Person();
				p.setUsername(rs.getString("screen_name"));
				p.setId(rs.getInt("person_id"));
				p.setPassword(rs.getString("password"));
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return p;
		
	}
	
}
