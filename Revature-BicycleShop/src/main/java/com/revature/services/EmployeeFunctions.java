package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.dao.BicycleCollections;
import com.revature.dao.BicycleDAO;
import com.revature.dao.BicycleDAOFactory;
import com.revature.dao.OfferDAO;
import com.revature.dao.OfferDAOFactory;

public class EmployeeFunctions implements EmployeeService {
	
	
	private BicycleDAO bicycleDAO;
	private OfferDAO offerDAO;

	public EmployeeFunctions() {
		BicycleDAOFactory bdf = new BicycleDAOFactory();
		bicycleDAO = bdf.getBicycleDAO();
		OfferDAOFactory odf = new OfferDAOFactory();
		offerDAO = odf.getOfferDAO();
	}
	
	public BicycleDAO getBicycleDAO() {
		return bicycleDAO;
	}
	
	public OfferDAO getOfferDAO() {
		return offerDAO;
	}
	
	//note: this is temporary.
	//this is just to establish a function that
	//I can override later on when taught about 
	@Override
	public boolean addABicycle(Bicycle bicycle) {
		return bicycleDAO.addABicycle(bicycle);
	}
	
	@Override
	public Offer acceptAnOffer(Offer offer) {
		Offer acceptedOffer = null;
		
		if (offerDAO.getAllOffers().contains(offer)) {
			HashSet<Offer> allOffers = (HashSet<Offer>)offerDAO.getAllOffers();
			
			
			for (Offer o: allOffers) {
				if (o.equals(offer)) {
					acceptedOffer = o;
					acceptedOffer.setStatus("accepted");
					acceptedOffer.getBicycleToBeSold().setStatus("owned");;
				}
				else if (o.getBicycleToBeSold() == offer.getBicycleToBeSold()) {
					offerDAO.removeAnOffer(o);
				}
				else {
					//do nothing here, because if the offer isn't the accepted offer
					//or related to the bicycle being sold
					//then the offer is irrelevant for processing.
				}
			}
		}
		
		return acceptedOffer;
	}
	
	@Override
	public Offer rejectAnOffer(Offer offer) {
		Offer rejectedOffer = null;
		
		if (offerDAO.getAllOffers().contains(offer)) {
			HashSet<Offer> allOffers = (HashSet<Offer>)offerDAO.getAllOffers();
			
			
			for (Offer o: allOffers) {
				if (o.equals(offer)) {
					rejectedOffer = o;
					rejectedOffer.setStatus("rejected");
					offerDAO.removeAnOffer(rejectedOffer);
					break;
				}
			}
		}
		
		return rejectedOffer;
	}
	

	
}
