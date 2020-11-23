package com.revature.controller;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.revature.beans.Offer;
import com.revature.controller.Application;
import com.revature.data.OfferPostgres;


public class Application_Test {
	
	
	// Test singleton method
	
	@DisplayName("Test single design pattern") 
	@Test
	public void singletonTest() {
		
		Set<Application> applications = new HashSet<Application>(); 
		Application testApplication0 = Application.getApplication(); 
		Application testApplication1 = Application.getApplication(); 
		Application testApplication2 = Application.getApplication();
		applications.add(testApplication0);
		applications.add(testApplication1);
		applications.add(testApplication2);
		
		Assertions.assertTrue(applications.size() == 1);
		
		Assertions.assertTrue(
				testApplication0.equals(testApplication1) && 
				testApplication1.equals(testApplication2)
				);
	}
	
	@DisplayName("Test initControllers() for customer case") 
	@Test
	public void initControllersTestA() {
		
		Application testApplication = Application.getApplication(); 
		
		testApplication.initControllers("customer");
		Assertions.assertTrue( testApplication.getCustomerController() != null );		
	}
	
	@DisplayName("Test initControllers() for employee case") 
	@Test
	public void initControllersTestB() {
		
		Application testApplication = Application.getApplication(); 
		
		testApplication.initControllers("employee");
		Assertions.assertTrue( testApplication.getEmployeeController() != null );		
	}
	

}
