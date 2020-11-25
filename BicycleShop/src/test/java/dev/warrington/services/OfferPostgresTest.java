package dev.warrington.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Offer;

public class OfferPostgresTest {

	@Test
	public void testAddOffer() {
		
		OfferService os = new OfferServiceImpl();
		
		Offer offer1 = new Offer(9, 3);
		Offer offer2 = new Offer(9, 3);
		offer1.setAmount(0.0);
		offer2.setAmount(0.0);
		
		Integer id = os.addOffer(offer1);
		
		assertNotEquals(id, offer2.getId());
		
		os.deleteOffer(offer1);
	}
	
	@Test
	public void testGetAllOffers() {
		
		OfferService os = new OfferServiceImpl();
		Offer offer1 = new Offer(9, 3);
		offer1.setAmount(0.0);
		
		os.addOffer(offer1);
		Set<Offer> allOffers = os.getAllOffers();
		
		assertTrue(allOffers.contains(offer1));
		os.deleteOffer(offer1);
		
	}
	
	@Test
	public void testDeleteOffer() {
		
		OfferService os = new OfferServiceImpl();
		Offer offer1 = new Offer(9, 3);
		offer1.setAmount(0.0);
		
		offer1.setId(os.addOffer(offer1));
		os.deleteOffer(offer1);
		Set<Offer> allOffers = os.getAllOffers();
		
		assertFalse(allOffers.contains(offer1));
		
	}
	
	@Test
	public void testDeleteGroupOfOffers() {
		
		OfferService os = new OfferServiceImpl();
		Offer offer1 = new Offer(9, 3);
		Offer offer2 = new Offer(9, 11);
		offer1.setAmount(0.0);
		offer2.setAmount(0.0);
		
		os.addOffer(offer1);
		os.addOffer(offer2);
		
		Set<Offer> fullOffers = os.getAllOffers();
		
		os.deleteGroup(9);
		
		Set<Offer> offersMinusGroup = os.getAllOffers();
		
		assertTrue(fullOffers.size() - offersMinusGroup.size() == 2);
		
	}
	
}