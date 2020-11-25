package dev.rev.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.data.PersonDAO;
import dev.rev.model.Person;
import dev.rev.model.Role;

public class PersonServiceTest {

	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("This will happen before any of the tests");
	}
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("This will happen before each test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("This will happen after each test");
	}
	
	@AfterAll
	public static void afterAllTests() {
		System.out.println("This will happen once after all of the tests");
	}
	
	@Test
	public void addperson() {
		
		Person per=new Person();
		per.setId(50);
		per.setName("Revature");
		per.setPassword("1");
		Role role= new Role();
		role.setId(1);
		role.setRole_name("Customer");
		per.setRole(role);
		per.setUsername("Rev1");
		
		PersonDAO pd = null;
		try {
			pd.add(per);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(per,pd.getByUsername(per.getUsername(), per.getPassword()));
		
		
	}
}
