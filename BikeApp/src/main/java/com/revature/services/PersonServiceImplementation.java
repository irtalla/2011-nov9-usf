package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImplementation implements PersonService{
	private PersonDAO personDao;
	
	public PersonServiceImplementation(){
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	public void updatePerson(Person p) {
		personDao.update(p);
		
	}

	public void deletePerson(Person p) {
		personDao.delete(p);
		
	}


}
