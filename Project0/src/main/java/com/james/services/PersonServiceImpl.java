package com.james.services;

import com.james.beans.Person;
import com.james.beans.Role;
import com.james.data.PersonDAO;
import com.james.data.PersonDAOFactory;
import com.james.exceptions.NonUniqueUsernameException;
import com.james.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username)  {
		Person human = new Person();
		ConnectionUtil  cu = ConnectionUtil.getConnectionUtil();
		try(Connection conn = cu.getConnection()) {
			String sql = "select * from person where username='" + username + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				human.setUsername(rs.getString("username"));
				human.setId(rs.getInt("id"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("user_role_id"));						
				human.setRole(job);
				return human;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		return null;
		//	return personDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		personDao.update(p);
	}

	@Override
	public void deletePerson(Person p) {
		personDao.delete(p);
	}

}
