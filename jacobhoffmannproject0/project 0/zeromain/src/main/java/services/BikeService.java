package services;

import java.util.Set;

import models.Bike;
import models.User;

public interface BikeService {
	// "create" method: returns the unique identifier of the added Cat
	public Integer addBike(Bike b);
	// "read" methods
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikes();
	public Set<Bike> getAvailableBikes();
	// "update" methods
	public void updateBike(Bike c);
	//public void adoptCat(User p, Bike c);
	// "delete" methods
	public void removeBike(Bike c);

}
