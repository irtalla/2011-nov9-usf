package com.revature.services;

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

public class OfferServiceImpl implements OfferService{
	private BicycleDAO bicycleDAO;
	private PersonDAO personDAO;
	private OfferDAO offerDAO;
	
	public OfferServiceImpl() {
		BicycleDAOFactory bicycleDAOFactory = new BicycleDAOFactory();
		bicycleDAO = bicycleDAOFactory.getBicycleDAO();
		
		PersonDAOFactory personDAOFactory = new PersonDAOFactory();
		personDAO = personDAOFactory.getPersonDAO();
		
		OfferDAOFactory offerDAOFactory = new OfferDAOFactory();
		offerDAO = offerDAOFactory.getOfferDAO();
	}
	
	@Override
	public Integer addOffer(Offer o, Bicycle b) {
		return offerDAO.add(o).getId();
	}

	@Override
	public Offer getOfferById(Integer id) {
		return offerDAO.getById(id);
	}

	@Override
	public Set<Offer> getOffers() {
		return offerDAO.getAll();
	}

	@Override
	public Set<Offer> getOffersByBicycle(Bicycle b) {
		return offerDAO.getOffersByBicycle(b);
	}

	@Override
	public Set<Offer> getOffersByPerson(Person p) {
		return offerDAO.getOffersByPerson(p);
	}

	@Override
	public void updateOffer(Offer o) {
		offerDAO.update(o);
	}

	@Override
	public void acceptOffer(Offer o) {
		Status accepted = new Status();
		accepted.setId(4);
		accepted.setName("Accepted");
		o.setStatus(accepted);
		offerDAO.update(o);
		
		System.out.println("Offer " + o.getId() + ": accepted");
		
		Bicycle b = o.getBicycle();
		
		// Rejects other pending offers when an offer is accepted
		Set<Offer> pendingOffers = offerDAO.getPendingBicycleOffers(b);
		for(Offer offer : pendingOffers) {
			if(!(offer.equals(o))){
				rejectOffer(offer);
				offerDAO.update(offer);
			}
		}
		b.setPrice(o.getAmount());
		bicycleDAO.update(b);
	}

	@Override
	public void rejectOffer(Offer o) {
		Status rejected = new Status();
		rejected.setId(5);
		rejected.setName("Rejected");
		o.setStatus(rejected);
		offerDAO.update(o);

		System.out.println("Offer " + o.getId() + ": rejected");
	}

	@Override
	public void deleteOffer(Offer o) {
		offerDAO.delete(o);
	}

}
