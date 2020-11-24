package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;


public class OfferServiceImpl implements OfferService {
	
	private OfferDAO offerDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
	}
	
	
	@Override
	public Offer add(Offer newOffer) {
		
		return offerDao.add(newOffer); 

		// TODO update offers collection. product service must call offer service... 
	}
	
	
	@Override
	public Set<Offer> getOffers() {
		System.out.println("Called getOffers() ");
		return null;
	}

	@Override
	public Set<Offer> getOffersByProductId(Integer productId) {
		System.out.println("Called getOffersByProductId() ");
		return null;
	}

	@Override
	public boolean acceptOffer(Integer offerId) {
		System.out.println("Called acceptOffer() ");
		return false;
	}

	@Override
	public boolean rejectOffer(Integer offerId) {
		System.out.println("Called rejectOffer() ");
		return false;
	}

}
