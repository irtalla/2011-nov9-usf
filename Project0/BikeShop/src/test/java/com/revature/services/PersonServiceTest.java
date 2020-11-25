package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;
import com.revature.data.PersonCollections;
import com.revature.data.PersonDAO;

public class PersonServiceTest {
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Before all tests");
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
	public void testPersonCollectionUpdate() {
		PersonDAO personDao = new PersonCollections();
		Person p = personDao.getById(1);
		p.setUsername("Cat");
		personDao.update(p);
		assertEquals(p, personDao.getById(p.getId()));
		System.out.println(personDao.getById(p.getId()));
	}

}
