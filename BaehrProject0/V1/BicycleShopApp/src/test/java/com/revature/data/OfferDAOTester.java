package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import com.revature.beans.Bicycle;
import com.revature.beans.User;
import com.revature.beans.Purchase;
import org.junit.jupiter.api.Test;
import com.revature.beans.Offer;
import org.junit.jupiter.api.Test;

class OfferDAOTester {

	private static OfferDAO offerDao;
	private static BicycleDAO bicycleDao;
	
	@BeforeAll
	public static void setUp() {

		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		bicycleDao = bicycleDaoFactory.getBicycleDAO();
	}
	
	@Test
	@Order(1)
	public void getByBicycleIdTest() {
		Set<Offer> offers = new HashSet<Offer>();
		offers = offerDao.getByBicycleId(20);
		assertTrue(offers.size() > 0);
	}
	
	@Test
	@Order(2)
	public void getByUserIdTest() {
		Set<Offer> offers = new HashSet<Offer>();
		offers = offerDao.getByUserId(4);
		assertTrue(offers.size() > 0);
	}

	@Test
	@Order(3)
	public void getByIdTest() {
		Offer offer = new Offer();
		offer = offerDao.getById(1);
		assertTrue(offer.getOffer_id() != 0);
	}
	
	@Test
	@Order(4)
	public void getAllTest() {
		Set<Offer> offers = new HashSet<Offer>();
		offers = offerDao.getAll();
		assertTrue(offers.size() > 0);
	}
	
	@Test
	@Order(5)
	void updateTest() {
		Offer o = offerDao.getById(1);
		Offer o2 = new Offer();
		o2.setBicycle(o.getBicycle());
		o2.setStatus(o.getStatus());
		o2.setOffer((o.getOffer() + 5));
		o2.setOffer_id(o.getOffer_id());
		o2.setUser_id(o.getUser_id());
		offerDao.update(o2);
		Offer o3 = offerDao.getById(1);;
		assertTrue(o3.getOffer() == o2.getOffer());
	}
	
	@Test
	@Order(7)
	void addAndDeleteTest() {
		Offer o =  new Offer();
		o.setBicycle(bicycleDao.getById(1));
		o.setOffer(o.getBicycle().getPrice());
		o.setUser_id(6);
		o.setStatus("Pending");
		Offer o2 = offerDao.add(o);
		Integer id = (o2.getOffer_id());
		assertTrue(id != null && id != 0);
		//System.out.println(id);
		offerDao.delete(o2);
		assertTrue(offerDao.getById(id) == null);
	}
	
//	@Test
//	@Order(8)
//	void rejectOtherOffersTest() {
//		Offer o = offerDao.getById(4);
//		Offer o2 = offerDao.getById(5); //testing offers for bicycle_id 20 since it has 3 offers hard coded in database
//		o.setStatus("Pending");
//		o2.setStatus("Pending");
//		offerDao.rejectOtherOffers(o);
//		o2 = offerDao.getById(5);
//		String s = o2.getStatus();
//		//System.out.println(s);
//		//System.out.println(s instanceof String);
//		//System.out.println("Rejected" instanceof String);
//		//System.out.println(s == "Rejected");
//		//System.out.println(s.equals("Rejected"));
//		assertTrue(s.equals("Rejected"));
//	}
	
	@Test
	@Order(9)
	void acceptOfferTest() {
		Offer o = offerDao.getById(4); //testing offers for bicycle_id 20 since it has 3 offers hard coded in database
		o.setStatus("Pending");
		offerDao.acceptOffer(o);
		Offer o2 = offerDao.getById(4);
		String s = o2.getStatus();
		assertTrue(s.equals("Accepted"));
	}
	
}
