package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;

public interface PersonServices {

		// create
		public Integer addPerson(Person p);
		//read
		public Person getByPersonID(Integer id);
		public Person getByUsername(String username);
		//public Set<Person> getByRoleID(Integer id);
		//update
		public void updatePerson(Person p);
		//delete
		public void deletePerson(Person p);
}
