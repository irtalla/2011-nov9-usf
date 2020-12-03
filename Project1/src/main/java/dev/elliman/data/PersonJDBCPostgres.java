package dev.elliman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.utils.DBConnectionUtil;

public class PersonJDBCPostgres implements PersonDAO {
	
	private DBConnectionUtil cu = DBConnectionUtil.getDBConnectionUtil();

	@Override
	public Person getPersonByUsername(String username) {
		Person person = null;
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from person where user_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				person = new Person();
				person.setId(rs.getInt("id"));
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
				person.setUsername(rs.getString("user_name"));
				person.setPassword(rs.getString("user_pass"));
				person.setAmountClaimed(rs.getDouble("amount_claimed"));
				
				Role r = new Role();
				r.setId(rs.getInt("user_role"));
				person.setRole(r);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return person;
	}
	
}
