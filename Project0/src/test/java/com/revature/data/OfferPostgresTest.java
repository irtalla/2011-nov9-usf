package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.*;
import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OfferPostgresTest {
	private OfferPostgres offerPostgres = new OfferPostgres();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();
	private PersonPostgres personPostgres = new PersonPostgres();
	

	@Test
	@Order(1)
	public void testAdd() {
		Bicycle bicycle = bicyclePostgres.getById(9);
		Person person = personPostgres.getById(8);
		Offer newOffer = new Offer(null, bicycle, person, 999.99);
		offerPostgres.add(newOffer);
		assertEquals(newOffer, offerPostgres.getById(6));
	}
	
	@Test
	public void testGetById() {
		Bicycle bicycle = bicyclePostgres.getById(1);
		Person person = personPostgres.getById(2);
		Offer retOffer = new Offer(1, bicycle, person, 5.00);
		assertEquals(retOffer, offerPostgres.getById(1));
	}
	
	@Test
	public void testGetAll() {
		assertEquals(5, offerPostgres.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Offer originalOffer = offerPostgres.getById(1);
		originalOffer.setPerson(personPostgres.getById(3));
		offerPostgres.update(originalOffer);
		assertEquals(originalOffer, offerPostgres.getById(1));
		originalOffer.setPerson(personPostgres.getById(2));
		offerPostgres.update(originalOffer);
	}
	
	@Test
	@Order(2)
	public void testDelete() {
		Integer size = offerPostgres.getAll().size();
		Offer deleteOffer = offerPostgres.getById(6);
		offerPostgres.delete(deleteOffer);
		assertEquals(size - 1, offerPostgres.getAll().size());
		offerPostgres.resetDefault();
	}
	
	@Test
	public void testGetOfferByBicycle() {
		Set<Offer> retOffers = new HashSet<Offer>();
		Offer retOffer = offerPostgres.getById(2);
		retOffers.add(retOffer);
		Bicycle b = bicyclePostgres.getById(5);
		assertEquals(retOffers, offerPostgres.getOfferByBicycle(b));
	}
	
	@Test
	public void getOfferByPerson() {
		Set<Offer> retOffers = new HashSet<Offer>();
		Offer retOffer = offerPostgres.getById(1);
		Person person = personPostgres.getById(2);
		retOffers.add(retOffer);
		assertEquals(retOffers, offerPostgres.getOfferByPerson(person));
	}

}
