package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.BicycleDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class BicycleServiceImpl implements BicycleService{
	private BicycleDAO bicycleDAO;
	private PersonDAO personDAO;
	private OfferDAO offerDAO;
	
	public BicycleServiceImpl() {
		BicycleDAOFactory bicycleDAOFactory = new BicycleDAOFactory();
		bicycleDAO = bicycleDAOFactory.getBicycleDAO();
		
		PersonDAOFactory personDAOFactory = new PersonDAOFactory();
		personDAO = personDAOFactory.getPersonDAO();
		
		OfferDAOFactory offerDAOFactory = new OfferDAOFactory();
		offerDAO = offerDAOFactory.getOfferDAO();
	}

	@Override
	public Integer addBicycle(Bicycle b) {
		return bicycleDAO.add(b).getId();
	}

	@Override
	public Bicycle getBicycleById(Integer id) {
		return bicycleDAO.getById(id);
	}

	@Override
	public Set<Bicycle> getBicycles() {
		return bicycleDAO.getAll();
	}

	@Override
	public Set<Bicycle> getAvailableBicycles() {
		return bicycleDAO.getAvailableBicycles();
	}

	@Override
	public void updateBicycle(Bicycle b) {
		bicycleDAO.update(b);
	}

	@Override
	public void deleteBicycle(Bicycle b) {
		bicycleDAO.delete(b);
	}

	@Override
	public Set<Bicycle> getBicyclesByOwner(Person p) {
		return bicycleDAO.getBicyclesByOwner(p);
	}

}
