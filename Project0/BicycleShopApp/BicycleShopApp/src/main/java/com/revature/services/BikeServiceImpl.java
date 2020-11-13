package com.revature.services;

import java.util.Set;

import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.models.Bike;

public class BikeServiceImpl implements BikeService {
	private BikeDAO bikeDao;
	
	public BikeServiceImpl() {
		// TODO Auto-generated constructor stub
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
	}

	public Integer addBike(Bike b) {
		// TODO Auto-generated method stub
		return null;
	}

	public Bike getBikeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Bike> getBikesByOwner(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateBike(Bike b) {
		// TODO Auto-generated method stub

	}

	public void deleteBike(Bike b) {
		// TODO Auto-generated method stub

	}

}
