package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Person;


public interface BikeService {
	public Integer addBike(Bike b);
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikes();
	public Set<Bike> getAvailableBikes();
	public void updateBike(Bike b);
	public void purchaseBike(Person p, Bike b);	
	public void removeBike(Bike b);
	
} 	
