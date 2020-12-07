package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import com.revature.beans.Person;
import com.revature.services.PersonService;

public class PersonServiceTest {
	PersonService personService = new PersonService();

	@Test
	public void testLogin() {
		String username = "tester";
		String password = "testpwd";
		
		Person retPerson = personService.login(username, password);
		Person expectedPerson = personService.getPersonByUsername(username);
		assertEquals(expectedPerson, retPerson);
	}
}
