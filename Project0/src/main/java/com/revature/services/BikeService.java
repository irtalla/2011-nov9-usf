package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.beans.Bike;

public interface BikeService {
	public Bike addBike(Bike b);
	
	public Bike getBikeById(Integer id);
	
	public Bike updateBike(Bike p) throws NonUniqueUsernameException;
	
	public void deleteBike(Bike p);
	
	public Set<Bike> getAvailableBikes();

//	public Bike getByModel(String model); 

	public Set<Bike> getAll();

	public Set<Bike> getUnavailableBikes();
}
