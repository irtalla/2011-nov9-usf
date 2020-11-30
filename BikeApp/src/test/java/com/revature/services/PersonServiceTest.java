package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;


public class PersonServiceTest {
	
	@Test
	public void testAddPerson() throws NonUniqueUsernameException{
		PersonService p = new PersonServiceImplementation();
		Person person = new Person();
		person.setUsername("customer1");
		person.setPassword("pass");
		Role r = new Role();
		r.setName("User");
		r.setId(2);
		person.setRole(r);
		p.addPerson(person);
		
		Set<Person> persons = new HashSet<>();
		persons.add((Person) p);
		assertTrue(persons.contains(persons));
	}

}
