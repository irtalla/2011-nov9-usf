package com.revature.services;

import java.util.Set;

import com.revature.beans.BicycleType;
import com.revature.data.BicycleTypeDAO;
import com.revature.data.BicycleTypeDAOFactory;

public class BicycleTypeServicesImpl implements BicycleTypeServices{
	
	private BicycleTypeDAO bicycleTypeDao;
	
	public BicycleTypeServicesImpl() {
		BicycleTypeDAOFactory bicycleTypeDaoFactory = new BicycleTypeDAOFactory();
		bicycleTypeDao = bicycleTypeDaoFactory.getBicycleTypeDAO();
	}

	@Override
	public Integer addBicycleType(BicycleType bt) {
		return bicycleTypeDao.add(bt);
	}

	@Override
	public Set<BicycleType> getAll() {
		return bicycleTypeDao.getAll();
	}

	@Override
	public BicycleType getById(Integer id) {
		return bicycleTypeDao.getById(id);
	}
	
	

}
