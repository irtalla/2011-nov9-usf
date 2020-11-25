package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class OfferServiceImpl implements OfferService {
	private OfferDAO offerDao;
//	private PersonDAO personDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		
//		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
//		personDao = personDaoFactory.getPersonDAO();
	}
	

	@Override
	public Integer addOffer(Offer o) {
	
		return offerDao.add(o).getId();
	}

	@Override
	public Offer getOfferById(Integer id) {
		
		return offerDao.getById(id);
	}

	@Override
	public Set<Offer> getOffers() {
		
		return offerDao.getAll();
	}

	@Override
	public void updateOffer(Offer o) {
		offerDao.update(o);
		
	}

	

	@Override
	public void acceptOffer(Offer o, Bike b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOffer(Offer o) {
		offerDao.delete(o);
		
	}

	@Override
	public Set<Offer> getAvailableOffers() {
	
		return offerDao.getAvailableOffers();
	}


	@Override
	public void removeAllOffersforBikeID(Integer id) {
		offerDao.deleteOfferForBikeId(id);;
		
	}


	@Override
	public Set<Offer> getAcceptedOffers() {
	
		return offerDao.getAcceptedOffers();
	}

}
