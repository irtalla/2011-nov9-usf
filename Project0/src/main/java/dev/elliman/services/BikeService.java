package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Person;

public interface BikeService {
	public Integer addBike(Bike bike);
	
	public Bike getById(Integer id);
	
	public Set<Bike> getAvalibleBikes();
	
	public Bike getByModel(String model);
	
	public void update(Bike bike);
	
	public void delete(Bike bike);
	
	public Set<Bike> getAll();
	
	public Set<Bike> getOwnedBikes(Person person);
}
