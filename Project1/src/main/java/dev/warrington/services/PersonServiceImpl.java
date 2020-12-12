package dev.warrington.services;

import dev.warrington.data.PersonDAO;
import dev.warrington.data.PersonPostgres;
import dev.warrington.exceptions.NonUniqueUsernameException;
import dev.warrington.beans.Person;

public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		personDao = new PersonPostgres();
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

}