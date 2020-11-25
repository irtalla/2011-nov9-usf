package services;

import java.util.Set;

import data.BikeDao;
import models.Bike;
import models.*;
public class BikeServiceImpl implements BikeService {
	// "create" method: returns the unique identifier of the added Cat
	private BikeDao bikeDao;
	public BikeServiceImpl() {
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
	}
	public Integer addBike(Bike b) {
		return bikeDao.add(b).getId();
	}
	// "read" methods
	public  Bike getBikeById(Integer id) {
		return bikeDao.getById(id);
	}
	public Set<Bike> getBikes(){
		return null;
	}
	public Set<Bike> getAvailableBikes(){
	return bikeDao.getAvailableBikes();	
	}
	// "update" methods
	public void updateBike(Bike c) {
		bikeDao.update(c);
	}
	//public void adoptCat(User p, Bike c);
	// "delete" methods
	public void removeBike(Bike c) {
		bikeDao.delete(c);
	}
}
