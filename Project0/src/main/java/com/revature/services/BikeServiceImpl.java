package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class BikeServiceImpl implements BikeService{

	private BikeDAO bikeDao;
	
	public BikeServiceImpl() {
		bikeDao = new BikeDAOFactory().getBikeDAO();
	}
	
	@Override
	public Bike addBike(Bike b) {
		return bikeDao.add(b);
	}

	@Override
	public Bike getBikeById(Integer id) {
		return bikeDao.getById(id);
	}

	@Override
	public Bike updateBike(Bike bike) throws NonUniqueUsernameException {
		return bikeDao.update(bike);
	}

	@Override
	public void deleteBike(Bike bike) {
		bikeDao.delete(bike);
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		return bikeDao.getAvailableBikes();
	}

	@Override
	public Set<Bike> getAll() {
		return bikeDao.getAll();
	}

	@Override
	public Set<Bike> getUnavailableBikes() {
		return bikeDao.getUnavailableBikes();
	}
}
