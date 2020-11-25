package com.bikeshop.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Person;
public class BikeServiceTest2 {
	BikeService bs = new BikeServiceImpl();

	// this method will run ONCE before any of your tests are run
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
	public void testGetInventoryTest() {

		
		AllServiceImpl asi = new AllServiceImpl();
		List<Bike> inv = asi.getInventory();
		
		System.out.println(inv.size());
		for (Bike b2 : inv) {
			System.out.println(b2.toString());
		}
		assertTrue(inv.size() > 0);
	}
//	
	@Test
	public void viewUserBikes () {
		Person p = new Person();
		p.setId(1);
		BikeService bs = new BikeServiceImpl();
		
		assertTrue(bs.viewUserBikes(p) != null);
	}
		
	
	@Test
	public void testAddandDelBike() {
		Bike b = new Bike();
		b.setManufacturer("Joe");
		b.setModel("The Bike");
		
		
		int id = bs.addBike(b);
		assertTrue(bs.delBike(id) == true);
		
		
	}
//	@Test
//	public void viewUserBikes() { 
//		
//	}

}
