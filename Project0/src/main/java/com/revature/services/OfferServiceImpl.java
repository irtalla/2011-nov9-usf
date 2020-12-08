package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;

public class OfferServiceImpl implements OfferService {
	private OfferDAO offerDao;
	
	public OfferServiceImpl() {
		offerDao = new OfferDAOFactory().getOfferDAO();
	}
	
	@Override
	public Offer makeOffer(Offer offer) {
		try {
			return offerDao.add(offer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Offer> getActiveOffersMadeByPerson(Person person) {
		return offerDao.getActiveOffersMadeByPerson(person);
	}

	@Override
	public Offer getOfferById(Integer id) {
		return offerDao.getById(id);
	}

	@Override
	public Offer acceptOffer(Offer o) {
		return offerDao.acceptOffer(o);
	}
	
	@Override
	public Offer rejectOffer(Offer o) {
		return offerDao.rejectOffer(o);
	}

	@Override
	public Set<Offer> getActiveOffersForBike(Bike b) {
		return offerDao.getActiveOffersForBike(b);
	}

	@Override
	public void deleteOffer(Offer t) {
		offerDao.delete(t);
	}

	@Override
	public Set<Offer> getAll() {
		return offerDao.getAll();
	}

	@Override
	public Offer addOffer(Offer t) throws Exception {
		return offerDao.add(t);
	}

	@Override
	public Set<Offer> getAllActiveOffers() {
		return offerDao.getAllActiveOffers();
	}

	@Override
	public Offer getAcceptedOfferForBike(Bike b) {
		return offerDao.getAcceptedOfferForBike(b);
	}
}
