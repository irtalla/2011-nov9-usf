package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
	public Person addPerson(Person p) {
		return personDao.add(p);
	}

	@Override
	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

}
