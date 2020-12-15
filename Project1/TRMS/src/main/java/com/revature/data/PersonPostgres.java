package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Title;
import com.revature.data.TitleDAO;
import com.revature.data.TitleDAOFactory;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

import exceptions.NonUniqueUsernameException;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person (username, passwd, user_role_id, title) values (?, ?, ?, ?)";
			
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			pstmt.setString(4, t.getTitle());
			
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
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person join user_role on user_role_id = user_role.id "
					+ "where person.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("person_id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("passwd"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				person.setRole(role);
				
				person.setEvtReqs(getEventsByPersonId(person.getId()));
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
				
				human.setEvtReqs(getEventsByPersonId(human.getId()));
				
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
				//!!!
				human.setEvtReqs(getEventsByPersonId(human.getId()));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from evt where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			
			// added
			sql = "delete from user_title where person_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();

			sql = "delete from req_fr_cmnt where person_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.executeUpdate();
			
			// end of additions
				
			sql = "delete from person where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
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
	
	
	public Set<EvtReq> getEventsByPersonId(Integer id){
		
		EvtReqDAO evtReqDao = new EvtReqPostgres();
		return evtReqDao.getEventsByPersonId(id);
		
	}

	public boolean isApprover(Integer person_id) {
		
		Set<Title> tset = new HashSet<>();
		TitleDAO tdao = new TitlePostgres();
		tset = tdao.getTitlesByPersonId(person_id);
		
		for (Title t : tset)
		{
			if (t.getName() != "employee")
				return true;
		}
		
		return false;
	}
	
}
