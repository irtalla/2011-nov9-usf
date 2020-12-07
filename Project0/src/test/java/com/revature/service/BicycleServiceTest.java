package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import com.revature.beans.Bicycle;
import com.revature.beans.Status;
import com.revature.data.BicyclePostgres;
import com.revature.data.PersonPostgres;
import com.revature.services.BicycleService;

public class BicycleServiceTest {
	private BicycleService bicycleService = new BicycleService();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();
	private PersonPostgres personPostgres = new PersonPostgres();
	
	@Test
	public void testRegisterOwner() {
		
		Bicycle bicycle = bicyclePostgres.getById(9);
		
		bicycleService.registerOwner(bicycle, personPostgres.getById(3));
		System.out.println(bicycle);
		System.out.println(bicyclePostgres.getOwnedBicycles(personPostgres.getById(3)));
		System.out.println(bicyclePostgres.getOwnedBicycles(personPostgres.getById(3)).contains(bicycle));
		assertTrue(bicyclePostgres.getOwnedBicycles(personPostgres.getById(3)).contains(bicycle));
		bicycleService.registerOwner(bicycle, null);
		Status status = new Status();
		status.setId(1);
		status.setStatus("avaliable");
		bicycle.setStatus(status);
		bicycleService.update(bicycle);
	}
}
