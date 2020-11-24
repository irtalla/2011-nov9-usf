package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;



public interface BicycleService {
	public Integer addBicycle(Bicycle o);
	// "read" methods
	public Bicycle getBicycleById(Integer id);
	public Set<Bicycle> getBicycles();
	// "update" methods
	public void updateBicycle(Bicycle o);
	// "delete" methods
	public void removeBicycle(Bicycle o);
}
