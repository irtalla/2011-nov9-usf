package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonCollections;
import com.revature.data.PersonDAOfactory;
import com.revature.data.PersonDao;
import com.revature.exception.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonServices{
private PersonCollections personDao;
	
	public PersonServiceImpl() {
		PersonDAOfactory personDaoFactory = new PersonDAOfactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		return personDao.add(t);
	}

	@Override
	public Person createUser(Person p) {
		return PersonDao.createUser(p.getName(), p.getType());
	}

	@Override
	public Person loginUser(String userId) {
		// TODO Auto-generated method stub
		return PersonDao.loginUser(userId);
	}

	
	
}
