package com.revature.services;

import java.util.Set;

import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.models.Offer;

public class OfferServiceImpl implements OfferService {
	private OfferDAO offerDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDao();
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
	public Set<Offer> getOfferByCustomer(Integer id) {
		return offerDao.getOfferByCustomer(id);
	}

	@Override
	public Set<Offer> getOfferByEmployee(Integer id) {
		return offerDao.getOfferByEmployee(id);
	}

	@Override
	public Set<Offer> getOfferByStatus(String status) {
		return offerDao.getOfferByStatus(status);
	}

	@Override
	public void updateOffer(Offer o) {
		offerDao.update(o);
	}

	@Override
	public void removeOffer(Offer o) {
		offerDao.delete(o);
	}

}
