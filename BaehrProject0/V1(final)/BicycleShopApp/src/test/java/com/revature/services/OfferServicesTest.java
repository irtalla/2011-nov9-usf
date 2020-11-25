package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.Purchase;
import com.revature.beans.Offer;

import org.junit.jupiter.api.Test;

class OfferServicesTest {
	
	private static OfferServicesImpl oserv;
	
	@BeforeAll
	public static void setUp(){
		oserv = new OfferServicesImpl();
	}

	@Test
	@Order(1)
	void getOfferByOfferIdTest() {
		Offer o = new Offer();
		o = oserv.getOfferByOfferId(1);
		assertTrue(o.getOffer_id() == 1);
	}

	@Test
	@Order(2)
	void getOfferByUserIdTest() {
		Set<Offer> offers = new HashSet<Offer>();
		offers = oserv.getByUserID(3);// user with id 3 has offers hard coded into SQL script for testing purposes
		assertTrue(offers.size() > 0);
	}

	@Test
	@Order(3)
	void getAllTest() {
		Set<Offer> offers = new HashSet<Offer>();
		offers = oserv.getAll();
		assertTrue(offers.size() > 0);
	}
	
	@Test
	@Order(4)
	void updateOfferTest() {
		Offer o = oserv.getOfferByOfferId(1);
		//System.out.println(o);
		Bicycle b2 = new Bicycle();
		BicycleType bt2 = new BicycleType();
		bt2.setId(8);
		bt2.setManufacturer("Polygon");
		bt2.setModel("Cascade 4");
		b2.setBicycleType(bt2);
		b2.setId(20);
		b2.setOwner_id(2);
		b2.setPrice(3200.0);
		b2.setYear(20180);
		b2.setStatus("Sold");
		Offer o2 = new Offer();
		o2.setBicycle(b2);
		o2.setOffer_id(1);
		o2.setOffer(9999.0);
		o2.setStatus("Accepted");
		o2.setUser_id(1);
		oserv.updateOffer(o2);
		Offer o3 = oserv.getOfferByOfferId(1);
		System.out.println(o2);
		System.out.println(o3);
		assertTrue(o3.getStatus().equals("Accepted"));
		assertTrue(o3.getOffer() == 9999.0);
		assertTrue(o3.getUser_id().equals(1));
		System.out.println(o2);
		System.out.println(o3);
		//following changes for idempotence
		o.setOffer(450.0);
		o.setUser_id(7);
		o.setStatus("Accepted");
		//System.out.println(o);
		oserv.updateOffer(o);
	}

	@Test
	@Order(5)
	public void addAndDeleteTest() {
		Bicycle b2 = new Bicycle();
		BicycleType bt2 = new BicycleType();
		bt2.setId(8);
		bt2.setManufacturer("Polygon");
		bt2.setModel("Cascade 4");
		b2.setBicycleType(bt2);
		b2.setId(20);
		b2.setOwner_id(2);
		b2.setPrice(3200.0);
		b2.setYear(20180);
		b2.setStatus("Sold");
		Offer o2 = new Offer();
		o2.setBicycle(b2);
		o2.setOffer(9999.0);
		o2.setStatus("Accepted");
		o2.setUser_id(1);
		//System.out.println(o2);
		oserv.addOffer(o2);
		//System.out.println(o2);
		Integer id = o2.getOffer_id();
		//System.out.println(id);
		assertTrue(id != null && id != 0); //tests add method
		
		oserv.delete(o2);
		Offer o = oserv.getOfferByOfferId(id);
		assertTrue(o == null); //test delete method
		
	}
	
	@Test
	@Order(6)
	public void acceptTest() {
		Offer o = oserv.getOfferByOfferId(3); // 3 is an offer for bicycle_id 20 with status "Pending" hard coded in SQL script for testing
		oserv.acceptOffer(o);
		Offer o2 = oserv.getOfferByOfferId(4); // 4 is an offer for bicycle_id 20 with status "Pending" hard coded in SQL script for testing
		Offer o3 = oserv.getOfferByOfferId(5); // 5 is an offer for bicycle_id 20 with status "Pending" hard coded in SQL script for testing
		Offer o4 = oserv.getOfferByOfferId(3);
//		System.out.println(o4);
//		System.out.println(o2);
//		System.out.println(o3);
		assertTrue(o4.getStatus().equals("Accepted"));
		assertTrue(o2.getStatus().equals("Rejected"));
		assertTrue(o3.getStatus().equals("Rejected"));
		//following changes for idempotence, resetting offers with offer_id 3,4,5 to pending status
		o.setStatus("Pending");
		oserv.updateOffer(o);
		o2.setStatus("Pending");
		oserv.updateOffer(o2);
		o3.setStatus("Pending");
		oserv.updateOffer(o3);
	}

}
