package dev.elliman.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;

public class PersonDAOTest {
	
	private static PersonDAO personDAO;

	private static Person testPerson;
	
	@BeforeAll
	public static void beforeAll() {
		personDAO = PersonDAOFactory.getPersonDAO();
		
		testPerson = new Person();
		testPerson.setId(0);
		testPerson.setUsername("test");
		testPerson.setPassword("password");
		testPerson.setFirstName("first");
		testPerson.setLastName("last");
		testPerson.setAmountClaimed(0D);
		Role role = new Role();
		role.setId(1);
		role.setName("role");
		testPerson.setRole(role);
	}
	
	@Test
	public void getPersonByUsername() {
		Person p = personDAO.getPersonByUsername(testPerson.getUsername());
		
		assertEquals(testPerson, p);
	}
}
