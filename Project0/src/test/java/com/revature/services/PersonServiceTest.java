package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceTest {
	private Person person1;
	private Person person2;
	private Role employee;
	private Role customer;
	private PersonService personService;
	
	@BeforeAll
	public void initializeService() {
		personService = new PersonServiceImpl();
	}
	
	@BeforeEach
	public void initializeData() {
		person1 = new Person();
		person1.setUsername("person 1");
		
		employee = new Role();
		person1.setRole(employee);
		
		person2 = new Person();
		person2.setUsername("person 2");
		
		customer = new Role();
		person2.setRole(customer);
	}
	
	@Test
	public void testAddPerson() throws NonUniqueUsernameException {
		Person updatedPerson1 = personService.addPerson(person1);
		assertNotNull(person1);
	
		Person updatedPerson2 = personService.addPerson(person1);
		assertNotNull(person1);
		
		//assert that adding a user with a duplicate username throws exception
		Person person3 = new Person();
		person3.setUsername("person 2");
		assertThrows(NonUniqueUsernameException.class, () -> personService.addPerson(person3));
	}
	
	@Test
	public void testGetAllPeople() throws NonUniqueUsernameException {
		Person updatedPerson1 = personService.addPerson(person1);
		Person updatedPerson2 = personService.addPerson(person1);
		
		Set<Person> allPeople = personService.getAll(); 
		assertTrue(allPeople.contains(updatedPerson1));
		assertTrue(allPeople.contains(updatedPerson2));
	}
	
	@Test
	public void testDeletePerson() throws NonUniqueUsernameException {
		Person updatedPerson1 = personService.addPerson(person1);
		personService.deletePerson(updatedPerson1);
		
		assertTrue(!personService.getAll().contains(updatedPerson1));
	}
}
