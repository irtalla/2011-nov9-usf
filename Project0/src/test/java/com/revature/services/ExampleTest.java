package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import com.revature.beans.Product;
import com.revature.data.ProductPostgres;
/*
 * By default, 
 * 
 */
import com.revature.utils.ConnectionUtil_Tests;

public class ExampleTest {
	
	public int returnInt() {
		return 2; 
	}
	
	// this method will run ONCE before any of your tests are run 
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("This will happen before all tests"); 
	}
	
	@BeforeEach 
	public void beforeEachTest() {
		System.out.println("This will happen before each test"); 
	}
	
	/*
	 * This method will run ONCE after all any of your tests are run. For
	 * example, closing the scanner or files. 
	 */
	@AfterAll
	public static void afterAllTests() {
		System.out.println("This will happen after all tests"); 
	}
	
	
	/*
	 * Assertion Methods: 
	 * 	assertEquals
	 *  assertTrue
	 *  assertFalse
	 *  assertArrayEquals
	 *  assertThrows
	 * 
	 */
	
	
	
	/*
	 * This method will run ONCE a
	 */
	@AfterEach 
	public void afterEachTest() {
		System.out.println("This will happen after each test"); 
	}
	
	
	@DisplayName("Simply assertEquals test") 
	@Test
	public void testTheThing() {
		
		Assertions.assertEquals(2, this.returnInt()); 
		
	}
	@DisplayName("Simply assertTrue test")
	@Test
	public void testTheOtherThing() {
		
		double i = 99.99; 
		Assertions.assertTrue(i < 100);
	}
	
	@DisplayName("Simply assertThrows test")
	@Test
	public void testException() {
			
		Assertions.assertThrows(NullPointerException.class, () -> {
			Object x = null; 
			x.getClass();
			
		}); 
	}
		
	
	
}
