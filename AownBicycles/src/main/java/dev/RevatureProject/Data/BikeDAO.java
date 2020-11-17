package dev.RevatureProject.Data;

import java.util.Set;

import dev.RevatureProject.models.Bike;

public interface BikeDAO extends GenericDAO<Bike>{
	public Bike add(Bike c);
	public Set<Bike> getAvailableBike();
	
}
