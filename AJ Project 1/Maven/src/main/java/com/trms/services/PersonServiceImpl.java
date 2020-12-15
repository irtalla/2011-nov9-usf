package com.trms.services;

import com.trms.data.PersonDAO;
import com.trms.beans.Person;
import com.trms.data.PersonDAOFactory;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	public Integer addPerson(Person p) {
		
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
