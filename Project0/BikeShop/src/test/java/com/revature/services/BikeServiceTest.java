package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;

import com.revature.beans.Bike;
import com.revature.data.BikeCollections;
import com.revature.data.BikeDAO;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(Alphanumeric.class)
public class BikeServiceTest {
	
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Before all tests");
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
	
//	@Order(3)
	@DisplayName("Bike Name test")
	@Test
	public void testBikeGetterAndSetter() {
		Bike bk = new Bike();
		bk.setName("Pokemon");
		assertEquals("Pokemon", bk.getName());
	}

//	@Order(2)
	@DisplayName("NullPointerException test")
	@Test
	public void testException() {
		Bike bk = null;
		
		assertThrows(NullPointerException.class, () -> {
			bk.getName();
		});
	}
	
//	@Order(1)
	@DisplayName("Bike Condition test")
	@Test 
	public void testBikeConditionGetterAndSetter() {
		Bike bk = new Bike();
		bk.setCondition("New");
		assertEquals("New", bk.getCondition());
	}
	
	@Test
	public void testBikeCollectionUpdate() {
		BikeDAO bikeDao = new BikeCollections();
		Bike bk = bikeDao.getById(1);
		System.out.println(bikeDao.getAll());
		bk.setName("Huffy");
		bikeDao.update(bk);
		assertEquals(bk, bikeDao.getById(bk.getId()));
//		System.out.println(bikeDao.getById(bk.getId()));
	}

	

}
