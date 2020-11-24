package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonOfferTest {
	private Person person1;
	private Person person2;
	private Role employee;
	private Role customer;
	private Bike bike;
	private PersonService personService;
	private BikeService bikeService;
	private OfferService offerService;
	
	@BeforeAll
	public void initializeService() {
		personService = new PersonServiceImpl();		
		offerService = new OfferServiceImpl();		
	}
	
	@BeforeEach
	public void initializeData() throws NonUniqueUsernameException {
		person1 = new Person();
		person1.setUsername("person 1");
		
		employee = new Role();
		person1.setRole(employee);
		
		person2 = new Person();
		person2.setUsername("person 2");
		
		customer = new Role();
		person2.setRole(customer);
		
		bike = new Bike();
	}
	
	@Test
	public void testMakeOffer() throws NonUniqueUsernameException {
		Person updatedPerson1 = personService.addPerson(person1);
		assertNotNull(person1);
		
		Person updatedPerson2 = personService.addPerson(person1);
		assertNotNull(person1);
		
		Bike updatedBike = bikeService.addBike(bike);
		assertNotNull(bike);
		
		Offer newOffer = new Offer();
		newOffer.setPerson(person2);
		newOffer.setBike(updatedBike);
		
		Offer offerMade = offerService.makeOffer(newOffer);
		assertNotNull(offerMade);
		
		assertEquals(offerMade.getPerson(), updatedPerson2);
		assertEquals(offerMade.getBike(), updatedBike);
		
		assertTrue(bikeService.getBikeById(updatedBike.getId()).getOffers().contains(offerMade));	
	}
	
	@Test
	public void testAcceptOffer() throws NonUniqueUsernameException {
		Person updatedPerson1 = personService.addPerson(person1);
		assertNotNull(person1);
		
		Person updatedPerson2 = personService.addPerson(person1);
		assertNotNull(person1);
		
		Bike updatedBike = bikeService.addBike(bike);
		assertNotNull(bike);
		
		Offer newOffer = new Offer();
		newOffer.setPerson(person2);
		newOffer.setBike(updatedBike);
		
		Offer offerMade = offerService.makeOffer(newOffer);
		assertNotNull(offerMade);
		
		assertEquals(offerMade.getPerson(), updatedPerson2);
		assertEquals(offerMade.getBike(), updatedBike);
		
		assertTrue(bikeService.getBikeById(updatedBike.getId()).getOffers().contains(offerMade));	
	}
}
