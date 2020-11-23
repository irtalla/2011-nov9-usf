package dev.elliman.services;

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
		Offer offerFromDatabase = os.getOfferById(0);
	}
	
	@Order(2)
	@Test
	public void getAllOffers() {
		
	}
	
	@Order(3)
	@Test
	public void getAllOffersPerson() {
		
	}
	
	@Order(4)
	@Test
	public void getAllActiveOffersPerson() {
		
	}
	
	@Order(5)
	@Test
	public void getAllActiveOffersBike() {
		
	}
	
	@Order(6)
	@Test
	public void acceptOffer() {
		
	}
	
	@Order(7)
	@Test
	public void rejectOffer() {
		
	}
	
	@Order(8)
	@Test
	public void failedAccept() {
		//an emplyee cannot accept their own offer
	}
}
