package com.revature.data;

import java.sql.Connection;
import java.util.Set;

import com.revature.beans.Bike;


public interface BikeDAO extends GenericDAO<Bike>{
	public Bike add(Bike b);
	public Set<Bike> getAvailableBikes();
	public Set<Bike> getBikesByPersonId(Integer id); 
	public void edit(Bike t);
	public Set<Bike> getByOwnerId(Integer id); 
}
