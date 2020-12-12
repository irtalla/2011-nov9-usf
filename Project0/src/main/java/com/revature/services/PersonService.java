package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PersonService {
	//crud methods:
	public Person addPerson(Person p) throws NonUniqueUsernameException;
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public Person updatePerson(Person p) throws NonUniqueUsernameException;
	// delete
	public void deletePerson(Person p);
	public Set<Person> getAll();
}
