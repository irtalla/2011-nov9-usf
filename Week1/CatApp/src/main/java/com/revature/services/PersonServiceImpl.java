package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	
	
	
	
	public PersonServiceImpl() {
		// PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		// personDao = personDaoFactory.getPersonDao();
	}

	@Override
	public Integer addPerson(Person p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByUsername(String username) {
		// TODO Auto-generated method stub
		
		System.out.println("");
		
		return null;
	}

	@Override
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		
		int i=p.getId();
		p.setId(0);
		p.setPassword("");
		p.setRole(null);
		p.setUsername("");
		
		personDao.delete(p);
		
		
		
		
	}

}
