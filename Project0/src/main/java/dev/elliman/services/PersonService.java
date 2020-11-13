package dev.elliman.services;

import dev.elliman.beans.Person;

public interface PersonService {
		//create
		public Integer createUser(Person person);
		
		//read
		public Person getPersonById(Integer id);
		public Person getPersonByUsername(String username);
		
		//update
		public void updatePerson(Person p);
		
		//delete
		public void deletePerson(Person p);
}
