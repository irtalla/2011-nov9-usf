package com.revature.services;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

import com.revature.data.TitleDAO;
import com.revature.data.TitleDAOFactory;

import exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
private PersonDAO personDao;
private TitleDAO tileDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
		
		TitleDAOFactory titleDaoFactory = new TitleDAOFactory();
		tileDao = titleDaoFactory.getTitleDAO();
		
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
	

	public boolean isApprover(Integer person_id) {
		return 	personDao.isApprover(person_id);
	}
	
	public Set<EvtReq> getEventsByPersonId (Integer person_id){
		return personDao.getEventsByPersonId(person_id);		
	}
	
}
