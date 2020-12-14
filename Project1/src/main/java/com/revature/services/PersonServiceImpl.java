package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Role;
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

	@Override
	public Set<Person> getAllPeopleWithRole(Role role) {
		return getDao().getAllPeopleWithRole(role);
	}

	@Override
	public Set<Person> getAllEditorsWithRole(Role role, Genre genre) {
		return getDao().getAllEditorsWithRoleAndGenre(role, genre);
	}
	
	 
}
