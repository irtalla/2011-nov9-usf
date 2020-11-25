package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.data.BicycleDAO;
import com.revature.data.BicycleDAOFactory;

public class BicycleServicesImpl implements BicycleServices{
	
	private BicycleDAO bicycleDao;
	
	public BicycleServicesImpl() {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
	}

	@Override
	public Set<Bicycle> getAll() {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		return bicycleDao.getAll();
	}

	@Override
	public Set<Bicycle> getAvailable() {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		return bicycleDao.getAvailable();
	}

	@Override
	public Set<Bicycle> getByUserId(Integer id) {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		return bicycleDao.getByUserId(id);
	}

	@Override
	public Integer addBicycle(Bicycle b) {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		bicycleDao.add(b);
		return b.getId();
	}

	@Override
	public Bicycle getBicycleById(Integer id) {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		return bicycleDao.getById(id);
	}

	@Override
	public void updateBicycle(Bicycle b) {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		bicycleDao.update(b);
	}

	@Override
	public void deleteBicycle(Bicycle b) {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		BicycleDAO bicycleDao = bicycleDaoFactory.getBicycleDAO();
		bicycleDao.delete(b);
		
	}

}
