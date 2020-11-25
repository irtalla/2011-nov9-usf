package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Bicycle;
import dev.warrington.data.BikeDAO;
import dev.warrington.data.BikeDAOFactory;

public class BicycleServiceImpl implements BicycleService {
	
	private BikeDAO bikeDao;
	
	public BicycleServiceImpl() {
		
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
		
	}

	@Override
	public Integer addBicycle(Bicycle b) {

		return bikeDao.add(b).getId();
		
	}

	@Override
	public Bicycle getBicycleById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBicycle(Integer id) {
		
		bikeDao.updateBicycleStatus(id);
		
	}

	@Override
	public void deleteBicycle(Bicycle b) {
		
		bikeDao.delete(b);
		
	}

	@Override
	public Set<Bicycle> getAvailable() {
		
		return bikeDao.getAvailable();
		
	}

	@Override
	public Set<Bicycle> getAll() {
		
		return bikeDao.getAll();
		
	}
	
	@Override
	public Set<Bicycle> getMine(Integer id) {
		
		return bikeDao.getMine(id);
		
	}

	@Override
	public void updateOwnership(Integer bid, Integer cid) {
		
		bikeDao.updateOwners(bid, cid);
		
	}
	
}
