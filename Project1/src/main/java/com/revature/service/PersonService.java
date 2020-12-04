package com.revature.service;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Pitch;

public interface PersonService {
	// create
	public Integer addPerson(Person p);
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public Set<Pitch> getAllPitchesByPersonId (Integer id);

	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);

}
