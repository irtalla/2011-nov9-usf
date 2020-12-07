package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.*;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonPostgresTest {
	PersonPostgres personPostgres = new PersonPostgres();

	@Test
	@Order (1)
	public void testGetById() {
		Person person = new Person();
		person.setId(1);
		person.setUsername("jaydenthompson");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		
		assertEquals(person, personPostgres.getById(1));
	}
	
	@Test
	@Order (2)
	public void testGetAll() {
		assertEquals(personPostgres.getAll().size(), 11);
	}
	
	
	@Test
	@Order (3)
	public void testUpdate() {
		Person originalPerson = personPostgres.getById(11);
		Person person = new Person();
		person.setId(11);
		person.setUsername("test");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		personPostgres.update(person);
		
		assertNotEquals(originalPerson, personPostgres.getById(11));
		person.setUsername("tester");
		personPostgres.update(person);
	}
	
	@Test
	@Order (5)
	public void testDelete() {
		int currentPersonCount = personPostgres.getAll().size();
		Person person = new Person();
		person.setId(12);
		person.setUsername("add_test");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		personPostgres.delete(person);
		assertEquals(currentPersonCount -1, personPostgres.getAll().size());
	}

	
	
	@Test
	@Order (4)
	public void testAdd() {
		int currentPersonCount = personPostgres.getAll().size();
		Person person = new Person();
		person.setUsername("add_test");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		try {
			personPostgres.add(person);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		assertEquals(currentPersonCount + 1, personPostgres.getAll().size());
	}

	@Test
	@Order (5)
	public void testGetByUsername() {
		Person person = new Person();
		person.setId(11);
		person.setUsername("tester");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		
		assertEquals(person, personPostgres.getByUsername("tester"));
	}
	
	@Test
	@Order (7)
	public void testUpdatePassword() {
		Person person = new Person();
		person.setId(11);
		person.setUsername("tester");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		String originalPassword = personPostgres.getPassword(person);
		
		personPostgres.updatePassword(person, "password");
		
		assertNotEquals(personPostgres.getPassword(person), originalPassword);
		
		personPostgres.updatePassword(person, originalPassword);
	}
	
	@Test
	@Order (6)
	public void testGetPassword() {
		String passwordString = "testpwd";
		Person person = new Person();
		person.setId(11);
		person.setUsername("tester");
		Role role = new Role();
		role.setId(2);
		role.setName("employee");
		person.setRole(role);
		
		assertEquals(passwordString, personPostgres.getPassword(person));
	}
}
