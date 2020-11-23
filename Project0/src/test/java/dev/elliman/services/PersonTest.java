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
	public void getByIDTest() {
		Person admin = personService.getPersonByUsername("admin");
		assertTrue(personService.getPersonById(admin.getID()).getUsername().equals("admin"));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Add user tests
	@Order(3)
	@Test
	public void addNewPerson() {
		Person newUser = new Person("first1", "last", "testUser", "password", new Role());
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
	public void addSecondNewPerson() {
		Person newUser = new Person("first2", "last", "testUser2", "password", new Role());
		Integer userID;
		try {
			userID = personService.createUser(newUser);
			assertTrue(userID == 3);
		} catch (NonUniqueUsernameException e) {
			System.out.println("yep you caught me");
			e.printStackTrace();
		}
	}
	
	@Order(5)
	@Test
	public void addDupelicatePerson() {
		Person newUser = new Person("first3", "last", "testUser", "password", new Role());
		assertThrows(NonUniqueUsernameException.class, () -> {
			personService.createUser(newUser);
		});
	}
	
	@Order(6)
	@Test
	public void addFixedDupePerson() {
		Person newUser = new Person("first3", "last", "testUser3", "password", new Role());
		Integer userID;
		try {
			userID = personService.createUser(newUser);
			assertTrue(userID == 4);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//update user
	
	@Order(7)
	@Test
	public void updateUserTest() {
		String newName = "Will";
		Person testUser = personService.getPersonByUsername("testUser3");//use one of the test users
		testUser.setFirstName(newName);
//		System.out.println(adminWithName);
//		System.out.println(adminWithName.getRole().getID());
		personService.updatePerson(testUser);
		
		assertEquals(personService.getPersonByUsername("testUser3").getFirstName(), newName);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//update user
	@Order(8)
	@Test
	public void deleteUserTest() {
		//remove the admin
		Person admin = personService.getAdminUser();
		personService.deletePerson(admin);
		
		assertNull(personService.getAdminUser());
	}
}
