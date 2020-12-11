package com.revature.services;

import com.revature.beans.Person;

import exceptions.NonUniqueUsernameException;

public interface PersonService {
		// create
		public Integer addPerson(Person p) throws NonUniqueUsernameException;
		public Person getPersonById(Integer id);
		public Person getPersonByUsername(String username);
		// update
		public void updatePerson(Person p);
		// delete
		public void deletePerson(Person p);
}
