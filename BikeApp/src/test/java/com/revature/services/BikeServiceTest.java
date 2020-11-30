package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Type;


public class BikeServiceTest {
	
	@Test
	public void testAddBike(){
		BikeService b = new BikeServiceImplementation();
		Bike bike = new Bike();
		bike.setId(7);
		bike.setColor("green");
		bike.setYear(2000);
		bike.setPrice(199.99);
		Type type = new Type();
		type.setName("Racing");
		type.setId(2);
		bike.setType(type);
		Brand brand = new Brand();
		brand.setName("Fuji");
		brand.setId(3);
		bike.setBrand(brand);
		b.addBike(bike);
		
		Set<Bike> bikes = b.getBikes();
		
		assertTrue(bikes.contains(bike));
		
		b.removeBike(bike);
			
	}
	
	@Test
	public void testGetBikes(){
		BikeService b = new BikeServiceImplementation();
		Bike bike = new Bike();
		bike.setId(7);
		bike.setColor("green");
		bike.setYear(2000);
		bike.setPrice(199.99);
		Type type = new Type();
		type.setName("Racing");
		type.setId(2);
		bike.setType(type);
		Brand brand = new Brand();
		brand.setName("Fuji");
		brand.setId(3);
		bike.setBrand(brand);
		b.addBike(bike);
		
		Set<Bike> bikes = b.getBikes();
		
		assertTrue(bikes.contains(bike));
		
		b.removeBike(bike);
			
	}
	

}
