package dev.elliman.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.exceptions.NonUniqueUsernameException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class OfferTester {
	private static PersonService ps;
	private static BikeService bs;
	private static OfferService os;
	private static Person cust;
	private static Bike bike;
	
	
	@BeforeAll
	public static void beforeAllTests() {
		ps = new PersonServiceImpl();
		bs = new BikeServiceImpl();
		os = new OfferServiceImpl();
		
		cust = new Person("first", "last", "cust", "pass", new Role());
		bike = new Bike("trike", "red");
		
		try {
			ps.createUser(cust);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bs.addBike(bike);
	}
	
	@Order(1)
	@Test
	public void makeAndGet() {
		Offer offer = new Offer(cust, bike, 216);
		
		Integer offerId = os.makeOffer(offer);
		Offer offerFromDatabase = os.getOfferById(offerId);
		
		assertTrue(offer.equals(offerFromDatabase));
	}
	
	@Order(2)
	@Test
	public void getAllOffers() {
		Offer offer = new Offer(cust, bike, 221);
		Integer offerId = os.makeOffer(offer);
		//there are 2 offers in the database
		
		assertEquals(2, os.getActiveOffer().size());
	}
	
	@Order(3)
	@Test
	public void getAllOffersPerson() {
		assertEquals(os.getOffers(cust).size(), 2);
	}
	
	@Order(4)
	@Test
	public void getAllActiveOffersPerson() {
		assertEquals(os.getActiveOffer(cust).size(), 2);
	}
	
	@Order(5)
	@Test
	public void getAllActiveOffersBike() {
		assertEquals(os.getActiveOffer(bike).size(), 2);
	}
	
	@Order(6)
	@Test
	public void acceptOffer() {
		Offer o = os.getOfferById(2);//get the 221 offer
		os.acceptOffer(o.getId(), new Person("", "", "a", "b", new Role()));//the role is wrong but the check should not happen here
		o = os.getOfferById(2);//re-get it from the database
		
		assertEquals("Accepted", o.getStatus());
		assertEquals(os.getActiveOffer().size(), 0);//the second offer should have been rejected
	}
	
	@Order(7)
	@Test
	public void failedAccept() {
		//making an offer on the same bike but this should be avoided in the controller
		Offer offer = new Offer(cust, bike, 20);
		os.makeOffer(offer);
		assertFalse(os.acceptOffer(offer.getId(), cust));
	}
}
