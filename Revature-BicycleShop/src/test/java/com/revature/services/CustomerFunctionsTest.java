package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Offer;
import com.revature.dao.BicycleCollections;
import com.revature.dao.OfferCollections;
import com.revature.services.CustomerFunctions;

public class CustomerFunctionsTest {

	
	//why assertNotNull?  Have to make sure bicycles exist
	//and they exist only if the thing aren't there
	//but also test if they give same output?
	@Test
	public void testViewingAllThings() {
		CustomerFunctions cf = new CustomerFunctions();
		BicycleCollections bc = new BicycleCollections();
		
		assertNotNull(cf.viewAllAvailableBicycles());
		assertArrayEquals(cf.viewAllAvailableBicycles().toArray(), bc.getAllAvailableBicycles().toArray());
	}
	
	
	@Test
	public void testMakingAnOffer() {
		CustomerFunctions cf = new CustomerFunctions();
		OfferCollections oc = new OfferCollections();
		
		Set<Bicycle> bicyclesToChooseFrom = cf.viewAllAvailableBicycles();
		Bicycle bike = (Bicycle)(bicyclesToChooseFrom.toArray())[0];
		Customer customer = new Customer("zagreus", "hIsForHamazing");
		
		Offer offer = new Offer(customer, bike, 99.99);
		
		assertTrue(cf.makeAnOffer(offer));
	}
	
	@Test
	public void testMakingABadOffer() {
		CustomerFunctions cf = new CustomerFunctions();
		OfferCollections oc = new OfferCollections();
		
		Bicycle bike = new Bicycle("plaintains", "vapidity", "at the center of a restaurant", null, 8.99);
		Customer customer = new Customer("zagreus", "hIsForHamazing");
		
		Offer offer = new Offer(customer, bike, 99.99);
		
		assertFalse(cf.makeAnOffer(offer));
	}
	
	@Test
	public void testLookingAtYourBikesWhenYouHaveBikes() {
		Customer customer = new Customer("cloudOfDarkness", "cloudOfLight");
		CustomerFunctions cf = new CustomerFunctions(customer);
		Set<Bicycle> customerBikes = cf.viewAllAvailableBicycles();
		assertNotEquals(0, customerBikes.size());
	}
	
	@Test
	public void testLookingAtYourBikesWhenYouDontHaveBikes() {
		Customer cackletta = new Customer("cackletta", "fawful");
		CustomerFunctions cf = new CustomerFunctions(cackletta);
		
		HashSet<Bicycle> emptyHashSet = new HashSet<Bicycle>();
		
		assertArrayEquals(emptyHashSet.toArray(), cf.viewBicyclesYouOwn().toArray());
	}
	
}
