package dev.elliman.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.elliman.beans.Person;
import dev.elliman.services.PersonService;
import dev.elliman.services.PersonServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class PersonTest {

	private static PersonService personService;
	
	@BeforeAll
	public static void beforeAllTests() {
		personService = new PersonServiceImpl();
	}
	
	@Order(1)
	@Test
	public void checkAdminUser() {
		assertTrue(personService.getPersonByUsername("admin")!= null);
	}
	
	@Order(2)
	@Test
	public void addNewPerson() {
		Person newUser = new Person("first", "last", "testUser", "password", "customer");
		assertTrue(personService.createUser(newUser) == 1);
	}
	
	@Order(3)
	@Test
	public void addSecondNewPerson() {
		Person newUser = new Person("first", "last", "testUser2", "password", "customer");
		assertTrue(personService.createUser(newUser) == 2);
	}
	
	@Order(4)
	@Test
	public void addDupelicatePerson() {
		Person newUser = new Person("first", "last", "testUser", "password", "customer");
		
	}
}
