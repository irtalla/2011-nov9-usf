package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.User;


public interface BikeService {
	//create
	public Integer addBike(Bike b);
	
	//read
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikes();
	public Set<Bike> getAvailableBikes();
	public Set<Bike> getOwnedBikes();
	//update
	public void updateBike(Bike b);
	public void ownBike(User u, Bike b);
	
	//delete
	public void removeBike(Bike b);

	

}
