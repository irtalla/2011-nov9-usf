package com.revature.data;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cross.beans.Offer;

public class OfferPostgres_Test {

	
	@DisplayName("Test getOffersByStatus()") 
	@Test
	public void getOffersByStatusTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres(); 
		Set<Offer> pendingOffers = testOfferPostgres.getOffersByStatus("pending");
		Set<Offer> acceptedOffers = testOfferPostgres.getOffersByStatus("accepted");
		Set<Offer> rejectedOffers = testOfferPostgres.getOffersByStatus("rejected");
		Assertions.assertTrue( pendingOffers.size() > 0);
		Assertions.assertTrue( acceptedOffers.size() > 0);
		Assertions.assertTrue( rejectedOffers.size() > 0);
		pendingOffers.forEach(
				offer -> Assertions.assertEquals("pending", offer.getStatus().getName() )
				); 
		acceptedOffers.forEach(
				offer -> Assertions.assertEquals("accepted", offer.getStatus().getName() )
				); 
		rejectedOffers.forEach(
				offer -> Assertions.assertEquals("rejected", offer.getStatus().getName() )
				); 	
	}
	
	@DisplayName("Test add()") 
	@Test
	public void addTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres(); 
		Offer newOffer = new Offer(); 
		newOffer.setAmount(100.00);
		newOffer.setCustomerId(1);
		newOffer.setId(-1);
		newOffer.setProductId(1);
		newOffer = testOfferPostgres.add(newOffer); 
		Assertions.assertNotEquals(-1, newOffer.getId() );
		Assertions.assertEquals(100.00, newOffer.getAmount() ); 
		Assertions.assertEquals("pending", newOffer.getStatus().getName() ); 
	}
	
	@DisplayName("Test getById()") 
	@Test
	public void getByIdTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres(); 
		Offer returnedOffer = null;
		returnedOffer = testOfferPostgres.getById(1); 
		Assertions.assertNotEquals(null, returnedOffer );
	}
	
	
	@DisplayName("Test getAll()") 
	@Test
	public void getAllTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres(); 
		Set<Offer> returnedOffers = new HashSet<Offer>(); 
		returnedOffers = testOfferPostgres.getAll(); 
		Assertions.assertNotEquals(0, returnedOffers.size() );
	}
	
	@DisplayName("Test update()") 
	@Test
	public void updateTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres();
		Offer updatedOffer = new Offer(); 
		updatedOffer.setAmount(1000.00);
		updatedOffer.setId(5);
		updatedOffer.getStatus().setId(3);
		
		boolean didUpdate = testOfferPostgres.update(updatedOffer);
		

		Assertions.assertEquals(true, didUpdate );
	}
	
	@DisplayName("Test delete()") 
	@Test
	public void deleteTest() {
		
		OfferPostgres testOfferPostgres = new OfferPostgres();
		Offer offerToDelete = new Offer(); 
		offerToDelete.setId(6);
		boolean didDelete = testOfferPostgres.delete(offerToDelete);
		Assertions.assertEquals(true, didDelete );
	}
}
