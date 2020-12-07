package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cross.beans.Offer;
import com.cross.beans.Pitch;
import com.cross.beans.Purchase;
import com.revature.data.OfferDAOFactory;
import com.revature.data.ProductPostgres;
import com.revature.data.PurchasePostgres;

public class OfferServiceImpl_Test {

	OfferService testOfferServ = new OfferServiceImpl(); 
	
	@DisplayName("Test add()")
	@Test
	public void addTest() {
		
		Offer offer = new Offer(); 
		offer.setAmount(1000.00);
		offer.setCustomerId(2);
		offer.setProductId(10);
		
		Offer addedOffer = testOfferServ.add(offer);
		
		assertTrue( addedOffer != null ); 
		
	}
	
	@DisplayName("Test getAll()")
	@Test
	public void getAllTest() {
		Set<Offer> offers = testOfferServ.getAll(); 
		assertTrue( offers.size() > 0 ); 	
	}
	
	@DisplayName("Test getOffersByProductId()")
	@Test
	public void getOffersByProductIdTest() {
		
		Set<Offer> emptyOffers = testOfferServ.getOffersByProductId(-1);
		assertTrue (emptyOffers.size() == 0 ); 
		Set<Offer> offers = testOfferServ.getOffersByProductId(7);
		assertTrue( offers.size() > 0 );
		assertTrue ( ! offers.removeIf( offer -> offer.getCustomerId() != 7 ));
	}
	
	@DisplayName("Test acceptOffer()")
	@Test
	public void acceptOfferTest() {
		assertFalse( testOfferServ.acceptOffer(-1) );
		assertTrue( testOfferServ.acceptOffer(1) );
	}
	
	@DisplayName("Test rejectOffer()")
	@Test
	public void rejectOfferTest() {
		assertFalse( testOfferServ.rejectOffer(-1) );
		assertTrue( testOfferServ.rejectOffer(1) );
	}
	
	@DisplayName("Test getAllAcceptedOffers()")
	@Test
	public void getAllAcceptedOffers() {
		
		Set<Offer> offers = testOfferServ.getAllAcceptedOffers(); 
		assertTrue( offers.size() > 0 );
		assertFalse( offers.removeIf( offer -> 
					! offer.getStatus()
						   .getName()
						   .equalsIgnoreCase("ACCEPTED"))
				);
	}
	
}