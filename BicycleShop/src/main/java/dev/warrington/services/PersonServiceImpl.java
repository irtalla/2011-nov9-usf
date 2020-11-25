package dev.warrington.services;

import dev.warrington.beans.Person;
import dev.warrington.data.PersonDAO;
import dev.warrington.data.PersonDAOFactory;
import dev.warrington.exceptions.NonUniqueUsernameException;

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
	
}
