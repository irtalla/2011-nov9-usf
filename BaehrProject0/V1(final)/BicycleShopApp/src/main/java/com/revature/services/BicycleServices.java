package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;

public interface BicycleServices {
	
	public Set<Bicycle> getAll();
	
	public Set<Bicycle> getAvailable();
	
	public Set<Bicycle> getByUserId(Integer id); //returns all bicycles owned by a particular user
	
	// create
	public Integer addBicycle(Bicycle b);
	// read
	public Bicycle getBicycleById(Integer id);
	// update
	public void updateBicycle(Bicycle b);
	// delete
	public void deleteBicycle(Bicycle b);


}
