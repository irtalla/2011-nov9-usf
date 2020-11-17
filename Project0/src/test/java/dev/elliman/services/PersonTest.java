package dev.elliman.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.exceptions.NonUniqueUsernameException;
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
		Person newUser = new Person("first", "last", "testUser", "password", new Role());
		Integer userID;
		try {
			userID = personService.createUser(newUser);
			assertTrue(userID == 1);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
	}
	
	@Order(3)
	@Test
	public void addSecondNewPerson() {
		Person newUser = new Person("first", "last", "testUser2", "password", new Role());
		Integer userID;
		try {
			userID = personService.createUser(newUser);
			assertTrue(userID == 2);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
	}
	
	@Order(4)
	@Test
	public void addDupelicatePerson() {
		Person newUser = new Person("first", "last", "testUser", "password", new Role());
		assertThrows(NonUniqueUsernameException.class, () -> {
			personService.createUser(newUser);
		});
	}
	
	@Order(5)
	@Test
	public void addFixedDupePerson() {
		Person newUser = new Person("first", "last", "testUser3", "password", new Role());
		Integer userID;
		try {
			userID = personService.createUser(newUser);
			assertTrue(userID == 3);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
	}
}
