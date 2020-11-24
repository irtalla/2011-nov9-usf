package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Bike;

public interface BikeService {
	public Bike addBike(Bike b);
	// read
	public Bike getBikeById(Integer id);
	// update
	public Bike updateBike(Bike p);
	// delete
	public void deleteBike(Bike p);
	
	
	public Set<Bike> getAvalailableBikes();

//	public Bike getByModel(String model); 
	
	public Set<Bike> getAllBikes();
}
