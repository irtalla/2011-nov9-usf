package com.revature.services;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PersonService {
	// Create
	public Integer addPerson(Person p) throws NonUniqueUsernameException;
	
	// Read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	
	// Update
	public void updatePerson(Person p);
	
	// Delete
	public void deletePerson(Person p);
}
