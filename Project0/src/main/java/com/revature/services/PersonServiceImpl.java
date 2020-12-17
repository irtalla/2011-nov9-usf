package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService{
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
	public Person addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p);
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public Person updatePerson(Person p) throws NonUniqueUsernameException {
		return personDao.update(p);
	}

	@Override
	public void deletePerson(Person p) {
		personDao.delete(p);
	}

	@Override
	public Set<Person> getAll() {
		return personDao.getAll();
	}
}
