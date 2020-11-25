package dev.warrington.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Bicycle;

public class BicycleServiceTest {

	@Test
	public void testAddBicycle() {
		
		BicycleService bs = new BicycleServiceImpl();
		Bicycle bike1 = new Bicycle("test", "test", 0.00, "test");
		
		bike1.setId(bs.addBicycle(bike1));
		Set<Bicycle> bikes = bs.getAll();
		
		assertTrue(bikes.contains(bike1));
		
		bs.deleteBicycle(bike1);
		
	}
	
	@Test
	public void testGetAllBicycles() {
		
		BicycleService bs = new BicycleServiceImpl();
		Bicycle bike1 = new Bicycle("test", "test", 0.00, "test");
		
		bike1.setId(bs.addBicycle(bike1));
		Set<Bicycle> allBikes = bs.getAll();
		
		assertTrue(allBikes.contains(bike1));
		bs.deleteBicycle(bike1);
		
	}
	
	@Test
	public void testDeleteBicycle() {
		
		BicycleService bs = new BicycleServiceImpl();
		Bicycle bike1 = new Bicycle("test", "test", 0.00, "test");
		
		bike1.setId(bs.addBicycle(bike1));
		bs.deleteBicycle(bike1);
		Set<Bicycle> allBikes = bs.getAll();
		
		assertFalse(allBikes.contains(bike1));
		
	}
	
	@Test
	public void testGetAvailableBicycles() {
		
		BicycleService bs = new BicycleServiceImpl();
		Set<Bicycle> availableBikes = bs.getAvailable();
		Set<Bicycle> soldBikes = new HashSet<>();
		
		for (Bicycle bike : availableBikes) {
			
			if (bike.getStatus() == 2)
				soldBikes.add(bike);
			
		}
		
		assertTrue(soldBikes.size() == 0);
		
	}
	
	@Test
	public void testGetMyBicycles() {
		
		BicycleService bs = new BicycleServiceImpl();
		Set<Bicycle> noBikes = bs.getMine(3);
		Set<Bicycle> twoBikes = bs.getMine(11);
		
		assertTrue(noBikes.size() == 0);
		assertTrue(twoBikes.size() == 2);
	}
	
	@Test
	public void testUpdateBicycleStatus() {
		
		BicycleService bs = new BicycleServiceImpl();
		Set<Bicycle> bikes1 = bs.getAvailable();
		
		for (Bicycle bike : bikes1) {
			bs.updateBicycle(bike.getId());
		}
		
		Set<Bicycle> bikes2 = bs.getAvailable();
		
		assertTrue(bikes2.size() == 0 && bikes1.size() > 0);
		
	}
	
	@Test
	public void testUpdateOwners() {
		
		BicycleService bs = new BicycleServiceImpl();
		bs.updateOwnership(9, 3);
		Set<Bicycle> myBikes = bs.getMine(3);
		
		assertTrue(myBikes.size() == 1);
		
	}
	
}