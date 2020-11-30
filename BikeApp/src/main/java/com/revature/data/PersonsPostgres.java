package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonsPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Person t) {
		try (Connection conn = cu.getConnection()){
			String sql = "delete from bike_app_users where id = " + t.getId();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Person add(Person p) throws NonUniqueUsernameException {
		Person t = null;
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike_app_users where values (default, ? , ? ,?)";
			String[] keys = {"id"};
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1,  p.getUsername());
			pstmt.setString(2,  p.getPassword());
			pstmt.setInt(3,  p.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()){
				t = p;
				t.setId(rs.getInt(1));
				conn.commit();
			} else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public Person getByUsername(String username) {
		Person p = null;
		try (Connection conn = cu.getConnection()){
			String sql = "select bike_app_users.id as id, role.id, username, password"
					+ "role.name as role_name from bike_app_users"
					+ "join role on bike_app_users.role = role.id where username = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				p = new Person();
				p.setUsername(rs.getString("username"));
				p.setId(rs.getInt("id"));
				p.setPassword(rs.getString("password"));
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Person setNewOffer(Double offer) {
		// TODO Auto-generated method stub
		return null;
	}

}
