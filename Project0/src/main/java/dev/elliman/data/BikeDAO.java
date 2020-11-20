package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.Bike;

public interface BikeDAO {
	
	public Integer add(Bike b);
	
	public Bike getByID(Integer id);
	
	public Set<Bike> getAll();
	
	public void update(Bike b);
	
	public void delete(Bike b);
	
	public void getByModel(String model);
}
