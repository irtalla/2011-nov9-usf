package com.revature.services;

import com.revature.beans.Person;

public interface PersonService {
	// create
	public Person addPerson(Person p);
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);

}
