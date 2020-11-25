package com.revature.services;


import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDAOFactory = new PersonDAOFactory();
		personDao = personDAOFactory.getPersonDAO();
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
	public void updatePerson(Person p) {
		personDao.update(p);

	}

	@Override
	public void deletePerson(Person p) {
		personDao.delete(p);

	}

	

}
