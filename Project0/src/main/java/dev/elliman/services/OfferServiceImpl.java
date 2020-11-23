package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;
import dev.elliman.data.OfferDAO;
import dev.elliman.data.OfferDAOFactory;

public class OfferServiceImpl implements OfferService{
	private OfferDAO offerDAO;
	
	public OfferServiceImpl() {
		offerDAO = new OfferDAOFactory().getOfferDAO();
	}

	@Override
	public Integer makeOffer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffer(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffer(Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getOfferById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void acceptOffer(Integer id, Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectOffer(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
