package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.*;
import com.revature.beans.Bicycle;
import com.revature.beans.Category;
import com.revature.beans.Person;
import com.revature.beans.Status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BicyclePostgresTest {
	
	
	@Test
	@Order(1)
	public void testGetById() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Bicycle bicycleSample = new Bicycle();
		
		bicycleSample.setId(1);
		bicycleSample.setModelName("Mongoose Legion Freestyle");
		Category category = new Category();
		category.setId(1);
		category.setName("bmx");
		bicycleSample.setCategory(category);
		Status status = new Status();
		status.setId(1);
		status.setStatus("avaliable");
		bicycleSample.setStatus(status);
		
		Bicycle result = bicyclePostgres.getById(1);
		
		assertEquals(bicycleSample, result);	
	}

	@Test
	@Order(2)
	public void testGetAll() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Set<Bicycle> retBicycles =bicyclePostgres.getAll();
		assertEquals(9, retBicycles.size());
	}
	
	@Test
	@Order(5)
	public void testUpdate() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Bicycle bicycle = bicyclePostgres.getById(9);
		bicycle.setModelName("Redline 2021 Roam");
		bicyclePostgres.update(bicycle);
		Bicycle retBicycle = bicyclePostgres.getById(9);
		
		assertEquals(bicycle, retBicycle);

	}
	
	@Test
	@Order(4)
	public void testDelete() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Set<Bicycle> originalBicycles = bicyclePostgres.getAll();
		Set<Bicycle> lastestBicycles = new HashSet<Bicycle>();
		Bicycle bicycle = new Bicycle();
		bicycle.setId(10);
		bicyclePostgres.delete(bicycle);
		lastestBicycles = bicyclePostgres.getAll();
		assertEquals(originalBicycles.size() - 1, lastestBicycles.size());
	}
	
	
	@Test
	@Order(3)
	public void testAdd() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Set<Bicycle> originalBicycles = bicyclePostgres.getAll();
		Set<Bicycle> lastestBicycles = new HashSet<Bicycle>();
		Bicycle bicycle = new Bicycle();
		bicycle.setModelName("Haro Steel Reserve");
		Category category = new Category();
		category.setId(1);
		bicycle.setCategory(category);
		Status status = new Status();
		status.setId(1);
		bicycle.setStatus(status);
		Bicycle retValBicycle = bicyclePostgres.add(bicycle);
		bicycle.setId(retValBicycle.getId());
		lastestBicycles = bicyclePostgres.getAll();
		assertEquals(originalBicycles.size() + 1, lastestBicycles.size());
	}
	
	@Test
	@Order(6)
	public void testGetAvailableBicycles() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Set<Bicycle> retBicycles =bicyclePostgres.getAvailableBicycles();
		assertEquals(7, retBicycles.size());
	}
	

	@Test
	public void testUpdateOwner() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Bicycle bicycle = bicyclePostgres.getById(3);
		Person person = null;
		bicyclePostgres.updateOwner(bicycle, person);
	}
	
	@Test
	public void testGetOwnedBicycles() {
		BicyclePostgres bicyclePostgres = new BicyclePostgres();
		Set<Bicycle> bicycles = new HashSet<Bicycle>();
		bicycles.add(bicyclePostgres.getById(2));
		Person person = new Person();
		person.setId(2);
		
		Set <Bicycle> retBicycles = bicyclePostgres.getOwnedBicycles(person);
		assertEquals(bicycles, retBicycles);
	}

	
}
