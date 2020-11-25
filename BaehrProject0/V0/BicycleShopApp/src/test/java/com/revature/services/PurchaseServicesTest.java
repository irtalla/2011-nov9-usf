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

class PurchaseServicesTest {
	
	private static PurchaseServicesImpl pserv;
	
	@BeforeAll
	public static void setUp(){
		pserv = new PurchaseServicesImpl();
	}

	@Test
	@Order(1)
	void testGetById() {
		Purchase p = new Purchase();
		p = pserv.getById(1);
		assertTrue(p.getPurchase_id() == 1);
	}
	
	@Test
	@Order(2)
	void testGetByUserId() {
		Set<Purchase> purchases = new HashSet<Purchase>();
		purchases = pserv.getByUserId(6); //6 is a user with purchases hard coded in the SQL script for testing purposes
		//System.out.println(purchases);
		assertTrue(purchases.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<Purchase> purchases = new HashSet<Purchase>();
		purchases = pserv.getAll(); //6 is a user with purchases hard coded in the SQL script for testing purposes
		//System.out.println(purchases);
		assertTrue(purchases.size() > 0);
	}
	
	@Test
	@Order(4)
	void updatePurchaseTest() {
		Purchase p = pserv.getById(1);
		Bicycle b = p.getBicycle();
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
		Purchase p2 = new Purchase();
		p2.setBicycle(b2);
		p2.setPurchase_id(1);
		p2.setPrice(9999.0);
		p2.setOutstandingBalance(-99.0);
		p2.setUser_id(1);
		p2.setPaymentsRemaining(99);
		pserv.updatePurchase(p2);
		Purchase p3 = pserv.getById(1);
		assertTrue(p3.getOutstandingBalance() == -99.0);
		assertTrue(p3.getPaymentsRemaining() == 99);
		assertTrue(p3.getPrice() == 9999.0);
		assertTrue(p3.getUser_id().equals(1));
		assertTrue(p3.getBicycle().getBicycleType().getId() == 8);
		assertTrue(p3.getBicycle().getBicycleType().getManufacturer().equals("Polygon"));
		assertTrue(p3.getBicycle().getBicycleType().getModel().equals("Cascade 4"));
		//following changes for idempotence
		p.setBicycle(b);
		p.setUser_id(7);
		p.setPrice(250.0);
		p.setPaymentsRemaining(0);
		p.setOutstandingBalance(0.0);
		pserv.updatePurchase(p);
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
		Purchase p2 = new Purchase();
		p2.setBicycle(b2);
		p2.setPrice(9999.0);
		p2.setOutstandingBalance(-99.0);
		p2.setUser_id(1);
		p2.setPaymentsRemaining(99);
		
		pserv.addPurchase(p2);
		Integer id = p2.getPurchase_id();
		assertTrue(id != null && id != 0); //tests add method
		
		pserv.deletePurchase(p2);
		Purchase p = pserv.getById(id);
		assertTrue(p == null); //test delete method
		
	}
	
}
