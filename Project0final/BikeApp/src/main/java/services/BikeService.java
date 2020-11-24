package services;

import java.sql.Connection;
import java.util.Set;

import beans.Bikes;
import beans.Offers;
import beans.Usr;

public interface BikeService {
	public Integer addBike(Bikes b);
	public Bikes getBikeById(Integer id);
	public Set<Bikes> getBikes();
	public Set<Bikes> getAvailableBikes();
	public void updateBike(Bikes b);
	public void removeBike(Bikes b);
	}
