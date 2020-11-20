package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person add(Person t) {
		
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
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public Person getById(Integer id) {
		Person person = new Person();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id, person.username, person. passwd, "
					+ "user_role.id as role_id, user_role.name as role_name "
					+ "from person join user_role on person.user_role_id = user_role.id"
					+ "where person.id = " + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				person.setId(rs.getInt("id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				person.setRole(r);
			}
			
			sql = "select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, "
					+ "breed.name as breed_name from "
					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from "
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join breed on breed_id = breed.id"
					+ "join person_cat on cat_status.id = person_cat.cat_id"
					+ "where person_cat.person_id = " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Set<Cat> cats = new HashSet<>();
			while (rs.next()) {
				Cat cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				cats.add(cat);
			}
			
			person.setCats(cats);
			
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
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Person human = new Person();
				human.setId(rs.getInt("person_id"));
				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				
				human.setCats(getCatsByPersonId(human.getId(), conn));
				
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
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person "
					+ "join user_role on user_role_id = user_role.id where username = ?";
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
				human.setCats(getCatsByPersonId(human.getId(), conn));
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
		try (Connection conn = cu.getConnection()) {
			if (t != null) {
				conn.setAutoCommit(false);
				int rowsChanged = 0;
				String sql = "update person set username = ?, passwd = ?, user_role_id = ? where person.id = ?";
				String[] keys = {"id"};
				PreparedStatement pstmt = conn.prepareStatement(sql, keys);
				pstmt.setString(1, t.getUsername());
				pstmt.setString(2, t.getPassword());
				pstmt.setInt(3, t.getRole().getId());
				pstmt.setInt(4, t.getId());
				
				rowsChanged += pstmt.executeUpdate();
				 
				sql = "delete from person_cat where person_cat.person_id = ?";
				pstmt = conn.prepareStatement(sql); 
				pstmt.setInt(1, t.getId());
				
				rowsChanged += pstmt.executeUpdate();
				
				for (Cat c : t.getCats()) {
					sql = "insert into person_cat values ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, t.getId());
					pstmt.setInt(2, c.getId());
					
					rowsChanged += pstmt.executeUpdate();
				}
				
				if (rowsChanged > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	private Set<Cat> getCatsByPersonId(Integer id, Connection conn) throws SQLException {
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
	}

}
