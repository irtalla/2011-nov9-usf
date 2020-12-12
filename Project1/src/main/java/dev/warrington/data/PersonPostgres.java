package dev.warrington.data;

import dev.warrington.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dev.warrington.beans.Role;

import dev.warrington.beans.Person;

public class PersonPostgres implements PersonDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person getByUsername(String username) {
		
		Person person = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person "
					+ "join user_role on user_role_id = user_role.id where username = ?";
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
				job.setId(rs.getInt("person.role_id"));
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

}