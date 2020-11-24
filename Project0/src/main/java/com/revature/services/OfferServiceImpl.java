package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;


public class OfferServiceImpl implements OfferService {
	
	private OfferDAO offerDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory userDaoFactory = new OfferDAOFactory();
		offerDao = userDaoFactory.getOfferDAO();
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
	public void updateOffer(Offer o) {
		offerDao.update(o);
	}

	@Override
	public void removeOffer(Offer o) {
		offerDao.delete(o);
	}

	@Override
	public Set<Offer> getOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAvailableOffers() {
		// TODO Auto-generated method stub
		return null;
	}

}
