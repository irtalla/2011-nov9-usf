package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.BicyclePostgres;
import com.revature.data.OfferDAO;
import com.revature.data.OfferPostgres;
import com.revature.data.PersonDAO;
import com.revature.data.PersonPostgres;
import com.revature.exceptions.NonUniqueUsernameException;

public class OfferServiceImpl implements OfferService {
	private OfferDAO oDao;
	private PersonDAO pDao;
	private BicycleDAO bDao;
	
	public OfferServiceImpl() {
		oDao = new OfferPostgres();
		pDao = new PersonPostgres();
		bDao = new BicyclePostgres();
	}
	
	
	
	@Override
	public Integer addOffer(Offer o) throws NullPointerException, NonUniqueUsernameException {
		return oDao.add(o).getId();
	}

	@Override
	public Offer getOfferById(Integer id) throws NullPointerException {
		return oDao.getById(id);
	}
	public Set<Offer> getAllOffers(){
		return oDao.getAll();
	}

	@Override
	public void updateOffer(Offer o) {
		oDao.update(o);
	}

	@Override
	public void deleteOffer(Offer o) {
		oDao.delete(o);
	}
	

	@Override
	public void acceptOffer(Integer id) {
		oDao.accept(id);
		
	}


}
