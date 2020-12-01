package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;

public interface PersonService {
	// create
	public Person addPerson(Person p);
	// read
	public Set<Person> getAll(); 
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public void updatePerson(Person p);
	// delete
	public boolean deletePerson(Person p);

}
