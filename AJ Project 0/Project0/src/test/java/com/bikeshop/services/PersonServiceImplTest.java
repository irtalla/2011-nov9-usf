package com.bikeshop.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.bikeshop.beans.Person;
import com.bikeshop.dao.PersonDAO;

class PersonServiceImplTest {
	PersonService ps = new PersonServiceImpl();
	private PersonDAO pd;
	private static Person p = new Person();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void testAddPerson() {
		p.setUsername("jim");
		p.setPassword("asdf");
		p.setEmail("asdf@asdf.com");
		p.setRole("customer");
		p.setFirst("jim");
		p.setLast("jim");
		p.setId(pd.add(p).getId());		
		assertTrue(p.getId() > 0);
	}
	@Test
	@Order(2)
	void testGetPersonById() {
		assertTrue(ps.getPersonById (p.getId()).getId() > 0);
	}
	@Test
	@Order(3)
	void testGetPersonByUsername() {
		assertTrue(ps.getPersonByUsername (p.getUsername()).getId() > 0);
	}
	@Test
	@Order(4)
	void testUpdatePerson() {
		assertTrue(ps.updatePerson(p).getId()>0);
	}
	@Test
	@Order(5)
	void testDelete() {
		assertTrue(ps.delete(p));
	}
}
