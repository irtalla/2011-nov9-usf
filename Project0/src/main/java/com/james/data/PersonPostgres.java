package com.james.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.james.beans.Person;
import com.james.beans.Role;
import com.james.exceptions.NonUniqueUsernameException;
import com.james.utils.ConnectionUtil;

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
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Person getById(Integer id) {
		return null;
	} 

	@Override
	public Set<Person> getAll() {
		Set<Person> people = new HashSet<>();
		
				
		return people;
	}

	@Override
	public Person getByUsername(String username) {
		Person human = null;		
		
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
			String sql = "delete from person_cat where person_id = ?; delete from person where id = ?";
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
	
/*	private Set<Cat> getCatsByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Cat> cats = new HashSet<>();
		CatDAO catDao = new CatPostgres();
		
		String sql = "select * from person_cat where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Cat pet = catDao.getById(rs.getInt("cat_id"));
			cats.add(pet);
		}
		
		return cats;
	} */

}
