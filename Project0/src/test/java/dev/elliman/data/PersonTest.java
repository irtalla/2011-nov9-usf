package dev.elliman.data;

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
		System.out.println("Test 1");
		assertTrue(personService.getPersonByUsername("admin")!= null);
	}
	
	@Order(2)
	@Test
	public void addnewPerson() {
		System.out.println("Test 2");
		Person newUser = new Person("first", "last", "testUser", "password", "customer");
		assertTrue(personService.createUser(newUser) == 1);
	}
}
