package dev.rev.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import dev.rev.data.BicycleDAO;
import dev.rev.model.Bicycle;
import dev.rev.model.Offer;


public class BicyclePostgreTest {
	
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("This will happen before any of the tests");
	}
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("This will happen before each test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("This will happen after each test");
	}
	
	@AfterAll
	public static void afterAllTests() {
		System.out.println("This will happen once after all of the tests");
	}
	
	@Test
	public void getbyId() {
		
		Bicycle b= new Bicycle();
		b.setId(0);
		//b.setBicycle_status("owned");
		b.setBrand("RoadRunner");
		b.setColor("Blackihh");
		b.setQuantity(1);
		b.setPrice(200);
		BicycleService bd= new BicycleServiceImp();
		int id= 11;
		assertEquals(b,bd.getbyID(id));
		
		
		
		
		
	}
	
	@Test 
	public void getbyBrand() {

		Bicycle b= new Bicycle();
		b.setId(0);
		//b.setBicycle_status("owned");
		b.setBrand("Flamingo");
		b.setColor("red");
		b.setQuantity(2);
		b.setPrice(450);
		BicycleService bd= new BicycleServiceImp();
		String Brand= "Flamingo";
		assertEquals(b,bd.getBicyclebyBrand(Brand));
		
		
	}
	
	@Test 
	public void getofferbyID() {
		
		Offer of= new Offer();
		of.setOffer_id(38);
		of.setOffer_Bicycle_id(12);
		of.setOffer_Person_id(18);
		of.setOffer_price(200);
		of.setStatus("Pending");
		
		OfferServices os= new OfferServiceIm();
		int id= 38;
		assertEquals(of,os.getbyid(id));
	}
//	@Test 
//	public void getofferbyPersonID() {
//		
//		Offer of= new Offer();
//		of.setOffer_id(38);
//		of.setOffer_Bicycle_id(12);
//		of.setOffer_Person_id(18);
//		of.setOffer_price(200);
//		of.setStatus("Pending");
//		
//		OfferServices os= new OfferServiceIm();
//		int personid= 18;
//		assertEquals(of,os.getofferbyPersonID(personid));
//	}
	
	@Test 
	public void getbikeidbyID() {
		
		Offer of= new Offer();
		of.setOffer_id(16);
		of.setOffer_Bicycle_id(4);
		of.setOffer_Person_id(12);
		of.setOffer_price(140);
		of.setStatus("Rejected");
		
		OfferServices os= new OfferServiceIm();
		int id= 16;
		int getbike=4;
		assertEquals(getbike,os.bike_id_byofferid(id));
	}
	
	
	

}
