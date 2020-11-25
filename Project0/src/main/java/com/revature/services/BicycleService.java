package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface BicycleService {
	// Create
	public Integer addBicycle(Bicycle b);
	
	// Read
	public Bicycle getBicycleById(Integer id);
	public Set<Bicycle> getBicycles();
	public Set<Bicycle> getAvailableBicycles();
	public Set<Bicycle> getBicyclesByOwner(Person p);
	
	// Update
	public void updateBicycle(Bicycle b);
	
	// Delete
	public void deleteBicycle(Bicycle b);
}
