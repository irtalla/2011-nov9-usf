package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;

public interface BikeDAO extends GenericDAO<Bike> {
	public Bike add(Bike b);
	public Set<Bike> getAvailableBikes();
	public Set<Bike> getUnavailableBikes();
}
