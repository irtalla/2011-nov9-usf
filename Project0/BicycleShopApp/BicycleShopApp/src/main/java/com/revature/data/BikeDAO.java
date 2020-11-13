package com.revature.data;

import java.util.Set;

import com.revature.models.Bike;

public interface BikeDAO extends GenericDAO<Bike>{
	public Bike setOwnership(Bike t, Integer id);
	public Set<Bike> getBikesByOwner(Integer id);
}
