package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Offer;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class OfferServiceTest {
	private Offer offer1;
	private Offer offer2;
	private Role employee;
	private Role customer;
	private OfferService offerService;
	
	@BeforeAll
	public void initializeService() {
		offerService = new OfferServiceImpl();
	}
	
	@BeforeEach
	public void initializeData() {
		offer1 = new Offer();
		offer2 = new Offer();
	}
	
	@Test
	public void testAddOffer() throws Exception {
		Offer updatedOffer1 = offerService.addOffer(offer1);
		assertNotNull(offer1);
	
		Offer updatedOffer2 = offerService.addOffer(offer1);
		assertNotNull(offer1);
	}
	
	@Test
	public void testGetAllPeople() throws Exception {
		Offer updatedOffer1 = offerService.addOffer(offer1);
		Offer updatedOffer2 = offerService.addOffer(offer1);
		
		Set<Offer> allPeople = offerService.getAll(); 
		assertTrue(allPeople.contains(updatedOffer1));
		assertTrue(allPeople.contains(updatedOffer2));
	}
	
	@Test
	public void testDeleteOffer() throws Exception {
		Offer updatedOffer1 = offerService.addOffer(offer1);
		offerService.deleteOffer(updatedOffer1);
		
		assertTrue(!offerService.getAll().contains(updatedOffer1));
	}
}
