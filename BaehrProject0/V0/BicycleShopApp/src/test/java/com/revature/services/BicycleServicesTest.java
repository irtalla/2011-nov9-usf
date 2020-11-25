package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.User;

class BicycleServicesTest {

	private static BicycleServicesImpl bserv;
	
	@BeforeAll
	public static void setUp() {
		bserv = new BicycleServicesImpl();
	}

	@Test
	@Order(1)
	void getBicycleByIdTest() {
		Bicycle b = new Bicycle();
		b = bserv.getBicycleById(1);
		assertTrue(b.getId() == 1);
	}

	@Test
	@Order(2)
	void getBicycleByUserIdTest() {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		bikes = bserv.getByUserId(5); // 5 is a user with bicycles hard coded in the SQL script
		assertTrue(bikes.size() > 0);
	}
	
	@Test
	@Order(3)
	void getAvailableTest() {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		bikes = bserv.getAvailable(); 
		assertTrue(bikes.size() > 0);
	}
	
	@Test
	@Order(4)
	void getAllTest() {
		Set<Bicycle> bikes = new HashSet<Bicycle>();
		bikes = bserv.getAll(); 
		assertTrue(bikes.size() > 0);
	}
	
	@Test
	@Order(5)
	void updateBicycleTest() {
		Bicycle b = bserv.getBicycleById(20);
		BicycleType bt = b.getBicycleType();
//		bt.setId(9);
//		bt.setManufacturer("DiamondBack");
//		bt.setModel("Release 1");
//		b.setBicycleType(bt);
//		b.setId(20);
//		b.setOwner_id(1);
//		b.setPrice(3250.0);
//		b.setYear(2020);
//		b.setStatus("Available");
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
		bserv.updateBicycle(b2);
		Bicycle b3 = bserv.getBicycleById(20);
		assertTrue(b3.getStatus().equals("Sold"));
		assertTrue(b3.getPrice() == 3200.0);
		assertTrue(b3.getOwner_id().equals(2));
		assertTrue(b3.getBicycleType().getId() == 8);
		assertTrue(b3.getBicycleType().getManufacturer().equals("Polygon"));
		assertTrue(b3.getBicycleType().getModel().equals("Cascade 4"));
		//following changes for idempotence
		b.setBicycleType(bt);
		b.setId(20);
		b.setOwner_id(1);
		b.setPrice(3250.0);
		b.setYear(2020);
		b.setStatus("Available");
		bserv.updateBicycle(b);
	}

	@Test
	@Order(6)
	public void addAndDeleteTest() {
		Bicycle b = bserv.getBicycleById(20);
		BicycleType bt = b.getBicycleType();
		bt.setId(9);
		bt.setManufacturer("DiamondBack");
		bt.setModel("Release 1");
		b.setBicycleType(bt);
		b.setOwner_id(1);
		b.setPrice(3250.0);
		b.setYear(2020);
		b.setStatus("Available");
		
		bserv.addBicycle(b);
		Integer id =b.getId();
		assertTrue(id != null); //tests add method
		
		bserv.deleteBicycle(b);
		Bicycle b2 = bserv.getBicycleById(id);
		assertTrue(b2 == null); //test delete method
		
	}
	
	
}
