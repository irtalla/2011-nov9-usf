package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.data.*;

public class BikeServiceImpl implements BikeService {
	private BicycleDao bicycleDao;
	private PersonCollections personDao;
	
	public BikeServiceImpl() {
		BicycleDao bicycleDaoFactory = new BicycleDao();
		bicycleDaoFactory = BicycleDAOFactory.getBicycleDAO();
		
		PersonDAOfactory personDaoFactory = new PersonDAOfactory();
		personDao = personDaoFactory.getPersonDAO();
	}


    @Override
    public Bicycle getBicycleById(Integer id) {
        return BicycleDao.getById(id);
    }

    public Set<Bicycle> getMyBicycle() {
        return (Set<Bicycle>) BicycleDao.getMyBicycles();
    }
    @Override
    public Set<Bicycle> getAllBicycles() {
        return (Set<Bicycle>) bicycleDao.getAllBicycles();
    }

    public void addBicycle(Integer id, String name, String owner) {
        bicycleDao.addBicycle(id, name, owner);   
    }
   
    @Override
    public void removeBicycle(Integer id) {
        bicycleDao.removeBicycle(id);
    }
    @Override
    public void getBicyclesForUser(String user){
        bicycleDao.getBicyclesForUser(user);
    }

    @Override
    public Integer addBicyclInteger(Bicycle c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Bicycle> getBicycle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addBicyle(Integer id, String name, String owner) {
        // TODO Auto-generated method stub

    }

}
