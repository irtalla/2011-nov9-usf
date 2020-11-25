package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface BicycleService {
	// create
	public Integer addBicycle(Bicycle b) throws NullPointerException, NonUniqueUsernameException;
	// read
	public Bicycle getBicycleById(Integer id) throws NullPointerException;
	public Set<Bicycle> getBicycles();
	public Set<Bicycle> getAvailableBicycles();
	// update
	public void updateBicycle(Bicycle b);
	// delete
	public void deleteBicycle(Bicycle b);
}
