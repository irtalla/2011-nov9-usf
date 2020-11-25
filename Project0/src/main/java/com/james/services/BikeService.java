package com.james.services;

import com.james.beans.Bike;
import com.james.exceptions.NonUniqueUsernameException;

public interface BikeService {
	// create
		public Integer addBike(Bike b) throws NonUniqueUsernameException;
		// read
		public Bike getBikeById(Integer id);
		public Bike getBikeByName(String name);
		// update
		public void updateBike(Bike b);
		// delete
		public void deleteBike(Bike b);
}
