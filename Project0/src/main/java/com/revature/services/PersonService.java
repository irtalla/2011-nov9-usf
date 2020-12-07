package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonPostgres;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonService {
	private PersonPostgres personPostgres;

	public PersonService() {
		personPostgres = new PersonPostgres();
	}
	
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personPostgres.add(p).getId();
	}

	public Person getPersonById(Integer id) {
		return personPostgres.getById(id);
	}

	public Person getPersonByUsername(String username) {
		return personPostgres.getByUsername(username);
	}

	public void updatePerson(Person p) {
		personPostgres.update(p);
	}

	public void deletePerson(Person p) {
		personPostgres.delete(p);
	}
	
	public void updatePassword(Person p, String newPassword) {
		personPostgres.updatePassword(p, newPassword);
	}
	
	public Person login(String username, String password) {
		Person retVal = null;
		Person foundPerson = personPostgres.getByUsername(username);
		if (foundPerson == null) {
			return retVal;
		}
		String foundPassword = personPostgres.getPassword(foundPerson);
		if (foundPassword.equals(password)) {
			retVal = foundPerson;
		}
		return retVal;
	}
	
}
