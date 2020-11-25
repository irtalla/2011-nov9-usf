package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;

public class BikeServiceImpl implements BikeService {
	
	private BikeDAO bikeDao;
	
	public BikeServiceImpl() {
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
	}
	@Override
	public Integer addBike(Bike b) {
		return bikeDao.add(b).getId();
	}
	@Override
	public Bike getBikeById(Integer id) {
		return bikeDao.getById(id);
	}
	@Override
	public Set<Bike> getBikes() {
		return bikeDao.getAll();
	}
	@Override
	public Set<Bike> getAvailableBikes() {
		return bikeDao.getAvailableBikes();
	}
	@Override
	public void updateBike(Bike b) {
		bikeDao.update(b);
	}
	@Override
	public void removeBike(Bike b) {
		bikeDao.delete(b);
	}
}
