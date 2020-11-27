package com.revature.data;

import java.util.Set;

import com.revature.models.Bike;

public interface BikeDAO extends GenericDAO<Bike> {
	public Bike add(Bike t);
	public Set<Bike> getAvailableBikes();
}
