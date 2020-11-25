package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;

public interface BikeService {
	// "create" method: returns the unique identifier of the added Bicycle
		public Integer addBicyclInteger(Bicycle c);
		// "read" methods
		public Bicycle getBicycleById(Integer id);
		public Set<Bicycle> getBicycle();
		public Set<Bicycle> getAllBicycles();
		// "update" methods
		public void addBicyle(Integer id, String name, String owner);
		// "delete" methods
		public void removeBicycle(Integer id);
		public void getBicyclesForUser(String user);
}
