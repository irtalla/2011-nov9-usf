package com.revature.services;

import com.revature.beans.Request;
import java.util.Set;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PersonService {
	// create
	public Integer addPerson(Person p) throws NonUniqueUsernameException;
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public Set<Request> getRequests(Person p);

	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);
}
