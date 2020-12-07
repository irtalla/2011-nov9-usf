package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cross.beans.Offer;
import com.cross.beans.Person;
import com.revature.data.PersonDAOFactory;

public class PersonService_Test {
	
	PersonService testPersonServ = new PersonServiceImpl();
	
	@DisplayName("Test add()")
	@Test
	public void addTest() {
		
		Person person = new Person();
		person.setUsername("Tim");
		person.setUsername("Rockynights");
		person.getRole().setId(3);
		assertTrue( testPersonServ.addPerson(person) != null ); 
	}
	
	@DisplayName("Test getPersonById()")
	@Test
	public void getPersonByIdTest() {
		
		Person nullperson = testPersonServ.getPersonById(-1); 
		assertTrue ( nullperson == null ); 
		
		Person person = testPersonServ.getPersonById(1);
		assertTrue( person != null && person.getId() == 1 ); 
	}
	
	@DisplayName("Test getPersonByUsername()")
	@Test
	public void getPersonByUsernameTest() {
		
		Person nullperson = testPersonServ.getPersonByUsername("jfipasourpaojfs;dmf/m jrpqwoear"); 
		assertTrue ( nullperson == null ); 
		
		Person person = testPersonServ.getPersonByUsername("Ariel");
		assertTrue( person != null && person.getUsername().equalsIgnoreCase("Ariel") ); 
	}
	
	@DisplayName("Test deletePerson()")
	@Test
	public void deletePersonTest() {
		
		Person person = new Person();
		person.setUsername("Sam");
		person.setUsername("happy");
		person.setId(4);
		person.getRole().setId(3);
		assertTrue( testPersonServ.deletePerson(person) ); 
		
		person = new Person(); 
		person.setId(-1);
		assertFalse( testPersonServ.deletePerson(person) ); 
		
		
	}
	
	@DisplayName("Test getAll()")
	@Test
	public void getAllTest() {
		Set<Person> persons = testPersonServ.getAll(); 
		assertTrue( persons.size() > 0 ); 	
	}
}
