package com.revature.services;

import java.util.Set;

import com.revature.models.Bike;

public interface BikeService {
	public Integer addBike(Bike b);
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikesByOwner(Integer id);
	public void updateBike(Bike b);
	public void deleteBike(Bike b);
}
