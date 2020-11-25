package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;

public class OfferServiceImpl implements OfferService {
	
	private OfferDAO offerDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
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
	public Set<Offer> getOffersForBike(Bike b) {
		return offerDao.getOffersForBike(b);
	}
	@Override
	public void updateOffer(Offer o) {
		offerDao.update(o);
	}
	@Override
	public void acceptOffer(Offer o) {
		offerDao.acceptOffer(o);
	}
	@Override
	public void removeOffer(Offer o) {
		offerDao.delete(o);
	}
}
