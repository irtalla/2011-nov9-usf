package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.data.PersonDAOFactory;
import com.revature.data.PersonHibernate;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService {

	public PersonServiceImpl() {
		super(new PersonDAOFactory());
	}

	@Override
	PersonHibernate getDao() {
		return new PersonHibernate();
	}
	
	public Person addPerson(Person p) throws NonUniqueUsernameException {
		return this.getDao().addPerson(p);
	}

	public Person updatePerson(Person p) throws NonUniqueUsernameException {
		return this.getDao().updatePerson(p);
	}

	@Override
	public Person getByUsername(String username) {
		return getDao().getByUsername(username);
	}
	
	 
}
