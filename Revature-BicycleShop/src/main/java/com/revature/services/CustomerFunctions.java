package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.dao.BicycleDAO;
import com.revature.dao.BicycleDAOFactory;
import com.revature.dao.OfferDAO;
import com.revature.dao.OfferDAOFactory;

public class CustomerFunctions implements CustomerService{
	
	private BicycleDAO bicycleDAO;
	private OfferDAO offerDAO;
	

	public CustomerFunctions() {
		BicycleDAOFactory bdf = new BicycleDAOFactory();
		bicycleDAO = bdf.getBicycleDAO();
		OfferDAOFactory odf = new OfferDAOFactory();
		offerDAO = odf.getOfferDAO();
	}

	@Override
	public Set<Bicycle> viewAllAvailableBicycles() {
		return bicycleDAO.getAllAvailableBicycles();
	}
	
	
}
