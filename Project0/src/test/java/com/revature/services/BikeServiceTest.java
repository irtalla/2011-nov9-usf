package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class BikeServiceTest {
	private Bike bike1;
	private Bike bike2;
	private Role employee;
	private Role customer;
	private BikeService bikeService;
	
	@BeforeAll
	public void initializeService() {
		bikeService = new BikeServiceImpl();
	}
	
	@BeforeEach
	public void initializeData() {
		bike1 = new Bike();
		bike2 = new Bike();
	}
	
	@Test
	public void testAddBike() {
		Bike updatedBike1 = bikeService.addBike(bike1);
		assertNotNull(bike1);
	
		Bike updatedBike2 = bikeService.addBike(bike1);
		assertNotNull(bike1);
	}
	
	@Test
	public void testGetAllPeople() throws NonUniqueUsernameException {
		Bike updatedBike1 = bikeService.addBike(bike1);
		Bike updatedBike2 = bikeService.addBike(bike1);
		
		Set<Bike> allPeople = bikeService.getAll(); 
		assertTrue(allPeople.contains(updatedBike1));
		assertTrue(allPeople.contains(updatedBike2));
	}
	
	@Test
	public void testDeleteBike() throws NonUniqueUsernameException {
		Bike updatedBike1 = bikeService.addBike(bike1);
		bikeService.deleteBike(updatedBike1);
		
		assertTrue(!bikeService.getAll().contains(updatedBike1));
	}
}
