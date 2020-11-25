package dev.warrington.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Person;
import dev.warrington.beans.Role;
import dev.warrington.exceptions.NonUniqueUsernameException;

public class PersonServiceTest {

	@Test
	public void testAddPerson() throws NonUniqueUsernameException {
		
		PersonService ps = new PersonServiceImpl();
		Person person1 = new Person();
		Role role1 = new Role();
		person1.setUsername("test1");
		person1.setPassword("test1");
		role1.setId(1);
		role1.setName("customer");
		person1.setRole(role1);
		
		Person person2 = new Person();
		Role role2 = new Role();
		person2.setUsername("test1");
		person2.setPassword("test1");
		role2.setId(1);
		role2.setName("customer");
		person2.setRole(role2);
		
		person1.setId(ps.addPerson(person1));
		
		assertFalse(person1.getId() == person2.getId());
		
		ps.deletePerson(person1);
		
	}
	
	@Test
	public void testDeletePerson() throws NonUniqueUsernameException {
		
		PersonService ps = new PersonServiceImpl();
		Person person1 = new Person();
		Role role1 = new Role();
		person1.setUsername("test1");
		person1.setPassword("test1");
		role1.setId(1);
		role1.setName("customer");
		person1.setRole(role1);
		
		person1.setId(ps.addPerson(person1));
		ps.deletePerson(person1);
		
		Person person2 = ps.getPersonByUsername(person1.getUsername());
		
		assertFalse(person1.getId() == person2.getId());
	}
	
	@Test
	public void testGetPersonByUsername() throws NonUniqueUsernameException {
		
		PersonService ps = new PersonServiceImpl();
		Person person1 = new Person();
		Role role1 = new Role();
		person1.setUsername("test1");
		person1.setPassword("test1");
		role1.setId(1);
		role1.setName("customer");
		person1.setRole(role1);
		
		person1.setId(ps.addPerson(person1));
		
		Person person2 = ps.getPersonByUsername(person1.getUsername());
		
		assertTrue(person1.getId() == person2.getId());
		
		ps.deletePerson(person1);
		
	}
	
}