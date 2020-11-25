package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Purchase;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchaseDAOFactory;

public class OfferServicesImpl implements OfferServices{
	private OfferDAO offerDao;
	private PurchaseDAO purchaseDao;
	private Integer defaultNumPayments;
	
	public OfferServicesImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		PurchaseDAOFactory purchaseDaoFactory = new PurchaseDAOFactory();
		purchaseDao = purchaseDaoFactory.getPurchaseDAO();
		defaultNumPayments = 10;
	}

	@Override
	public Set<Offer> getAll() {
		return offerDao.getAll();
	}

	@Override
	public Set<Offer> getByUserID(Integer id) {
		return offerDao.getByUserId(id);
	}

	@Override
	public void addOffer(Offer o) {
		offerDao.add(o);
	}

	@Override
	public Offer getOfferByOfferId(Integer id) {
		return offerDao.getById(id);
	}

	@Override
	public void updateOffer(Offer o) {
		offerDao.update(o);
	}

	@Override
	public void delete(Offer o) {
		offerDao.delete(o);
	}

	@Override
	public Offer acceptOffer(Offer o) {
		Offer n = offerDao.acceptOffer(o);
		Purchase p = new Purchase();
		p.setBicycle(o.getBicycle());
		p.setPrice(o.getOffer());
		p.setUser_id(o.getUser_id());
		p.setOutstandingBalance(o.getOffer());
		p.setPaymentsRemaining(defaultNumPayments);
		purchaseDao.add(p);
		return n;
	}

	@Override
	public Set<Offer> getOutstanding() {
		return offerDao.getOutstanding();
	}

}
