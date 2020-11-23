package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;

public interface BikeService {
	public Integer addBike(Bike bike);
	
	public Bike getById(Integer id);
	
	public Set<Bike> getAvalibleBikes();
	
	public Bike getByModel(String model);
	
	public void update(Bike bike);
	
	public void delete(Bike bike);
}
