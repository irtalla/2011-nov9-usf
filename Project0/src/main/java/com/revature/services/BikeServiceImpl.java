package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.beans.Status;

import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;


public class BikeServiceImpl implements BikeService {
	private BikeDAO bikeDao;
	private UserDAO userDao;
	
	
	public BikeServiceImpl() {
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
		
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();
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
    public void ownBike(User u, Bike b) {
    	//Offer offer = new Offer();
    	Status status = new Status();
    	status.setId(2);
    	status.setName("owned");
    	b.setStatus(status);
    	updateBike(b);
        Set<Bike> set = u.getBikes();
        set.add(b);
        u.setBikes(set);
        userDao.update(u);
        //dump into user bike table
    }
    
    
    
    
    @Override
    public void removeBike(Bike b) {
        bikeDao.delete(b);
    }

}
