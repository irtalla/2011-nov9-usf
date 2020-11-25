package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
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

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;

public class ControllerServiceTest {
	private static PersonService pServ = new PersonServiceImpl();
	private static BicycleService bServ = new BikeServiceImpl();
	
	@Test
	public void testViewOwnedBikes() {
		Person p = new Person();
		Set<Bicycle> bikes = new HashSet<>();
		Person p2 = new Person();
		Bicycle b = new Bicycle();
		Bicycle b2 = new Bicycle();
		b.setName("test");
		bikes.add(b);
		p.setBikes(bikes);
		
		ControllerService.viewOwnedBikes(p);
		//true that bike exists
		assertEquals(true, p.getBikes().contains(b));
		//this bike is not in the person set
		assertEquals(false, p.getBikes().contains(b2));
		assertFalse(p2.getBikes().contains(b));
	}
	//mockito test 
	/*
	@Test
	public void testViewAvailableBikes() {
		Person p = new Person();
		
		Set<Bicycle> testBikes = new HashSet<>();
		testBikes = bServ.getAvailableBicycles();
		
		Bicycle b = new Bicycle();
		Status s = new Status();
		s.setName("available");
		b.setStatus(s);
		testBikes.add(b);;
		
		System.out.println(bServ.getAvailableBicycles());
		
		assertTrue(testBikes.contains(b));
		
	}
	*/
}
