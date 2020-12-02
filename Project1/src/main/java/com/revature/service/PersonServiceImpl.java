package com.revature.service;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonPostgres;

public class PersonServiceImpl implements PersonService {
	private PersonDAO pDao = new PersonPostgres();
	@Override
	public Integer addPerson(Person p) {
		return pDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return pDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return pDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		pDao.update(p);
	}

	@Override
	public void deletePerson(Person p) {
		pDao.delete(p);
	}

}
