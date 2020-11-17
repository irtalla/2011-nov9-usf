package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.dao.BicycleDAO;
import com.revature.dao.BicycleDAOFactory;
import com.revature.dao.OfferDAO;
import com.revature.dao.OfferDAOFactory;

public class CustomerFunctions implements CustomerService{
	
	private BicycleDAO bicycleDAO;
	private OfferDAO offerDAO;
	private Set<Bicycle> yourBicycles;
	private Set<Offer> yourOffers;
	
	private Customer whoeverYouAre;

	public CustomerFunctions() {
		BicycleDAOFactory bdf = new BicycleDAOFactory();
		bicycleDAO = bdf.getBicycleDAO();
		OfferDAOFactory odf = new OfferDAOFactory();
		offerDAO = odf.getOfferDAO();
	}
	
	public CustomerFunctions(Customer c) {
		BicycleDAOFactory bdf = new BicycleDAOFactory();
		bicycleDAO = bdf.getBicycleDAO();
		OfferDAOFactory odf = new OfferDAOFactory();
		offerDAO = odf.getOfferDAO();
		
		HashSet<Bicycle> yourBicycles = new HashSet<Bicycle>();
		HashSet<Offer> yourOffers = new HashSet<Offer>();
		
		for(Bicycle someBicycle: bicycleDAO.getAllAvailableBicycles()) {
			Customer assumedOwner = someBicycle.getWhoWillOwnTheBike();
			if (assumedOwner != null && assumedOwner.equals(c)) {
				yourBicycles.add(someBicycle);
			}
		}
		
		for(Offer someOffer: offerDAO.getAllOffers()) {
			Customer assumedOfferMaker = someOffer.getOfferMaker();
			if (assumedOfferMaker != null && assumedOfferMaker.equals(c)) {
				yourOffers.add(someOffer);
			}
		}
		
		this.yourBicycles = yourBicycles;
		this.yourOffers = yourOffers;
	}
	
	//pun is intended. It is literally the customization of a program
	//by adding a customer.
	@Override
	public void addCustomerization(Customer c) {
		HashSet<Bicycle> yourBicycles = new HashSet<Bicycle>();
		HashSet<Offer> yourOffers = new HashSet<Offer>();
		
		for(Bicycle someBicycle: bicycleDAO.getAllAvailableBicycles()) {
			Customer assumedOwner = someBicycle.getWhoWillOwnTheBike();
			if (assumedOwner != null && assumedOwner.equals(c)) {
				yourBicycles.add(someBicycle);
			}
		}
		
		for(Offer someOffer: offerDAO.getAllOffers()) {
			Customer assumedOfferMaker = someOffer.getOfferMaker();
			if (assumedOfferMaker != null && assumedOfferMaker.equals(c)) {
				yourOffers.add(someOffer);
			}
		}
		
		this.yourBicycles = yourBicycles;
		this.yourOffers = yourOffers;
	}
	
	@Override
	public boolean makeAnOffer(Offer o) {
		if (!bicycleDAO.getAllAvailableBicycles().contains(o.getBicycleToBeSold())) {
			return false;
		}
		
		boolean globallyAddingOffer = offerDAO.addAnOffer(o);
		boolean locallyAddingOffer = yourOffers.add(o);
		
		return globallyAddingOffer && locallyAddingOffer;
		
	}

	@Override
	public Set<Bicycle> viewAllAvailableBicycles() {
		return bicycleDAO.getAllAvailableBicycles();
	}
	
	@Override 
	public Set<Bicycle> viewBicyclesYouOwn() {
		return yourBicycles;
	}
	
	@Override
	public Set<Offer> viewOffersYouMade(){
		return yourOffers;
	}
}
