package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Offer;
import dev.warrington.data.OfferDAO;
import dev.warrington.data.OfferDAOFactory;

public class OfferServiceImpl implements OfferService {
	
	private OfferDAO offerDao;

	public OfferServiceImpl() {
		
		OfferDAOFactory offerDAOFactory = new OfferDAOFactory();
		offerDao = offerDAOFactory.getOfferDao();
		
	}
	
	@Override
	public Integer addOffer(Offer o) {
	
		return offerDao.add(o).getId();
		
	}

	@Override
	public Set<Offer> getAllOffers() {
		
		return offerDao.getAll();
		
	}

	@Override
	public Set<Offer> getMyOffers(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOffer(Offer o) {

		offerDao.delete(o);
		
	}

	@Override
	public void deleteGroup(Integer id) {
		
		offerDao.deleteAllOffersByBikeId(id);
		
	}

}