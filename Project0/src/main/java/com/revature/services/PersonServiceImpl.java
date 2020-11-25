package com.revature.services;

import java.sql.Connection;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.PersonPostgres;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
	public Integer addPerson(Person p) throws NullPointerException, NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) throws NullPointerException{
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		personDao.update(p);
	}

	@Override
	public void deletePerson(Person p) {
		personDao.delete(p);
	}
	public Set<Person> getAll() {
		return personDao.getAll();
	}
	


	
}
