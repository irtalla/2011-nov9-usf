package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface BikeService {
	// "create" method: returns the unique identifier of the added Cat
	public Integer addBike(Bike b);
	// "read" methods
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikeByOwnerId(Integer id);
	public Set<Bike> getBikes();
	public Set<Bike> getBikesByPersonId(Integer id);
	public Set<Bike> getAvailableBikes();
	// "update" methods
	public void updateBike(Bike b);
	public void editBike(Bike b);
	public Offer bidBike(Person p, Bike b, float f);
	public void purchaseBike(Person p, Bike b);
	// "delete" methods
	public void removeBike(Bike b);
}
