package com.revature.services;

import com.revature.beans.Person;

import com.revature.exceptions.NonUniqueUsernameException; 


public interface PersonService {
	public Person addPerson(Person p) throws NonUniqueUsernameException;
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public void updatePerson(Person p);
	public void deletePerson(Person p);
}
 	