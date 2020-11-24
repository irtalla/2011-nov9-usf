package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike updateBike(Bike bike) {
		return bikeDao.update(bike);
	}

	@Override
	public void deleteBike(Bike bike) {
		bikeDao.delete(bike);
	}
	
	@Override
	public Set<Bike> getAvalailableBikes() {
		return bikeDao.getAvailableBikes();
	}

//	@Override
//	public Bike getByModel(String model) {
//		return bikeDao.getByModel(model);
//	}

	@Override
	public Set<Bike> getAllBikes() {
		return bikeDao.getAll();
	}
}
