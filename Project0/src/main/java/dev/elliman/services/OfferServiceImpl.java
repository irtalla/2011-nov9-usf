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
		return offerDAO.makeOffer(offer);
	}

	@Override
	public Set<Offer> getActiveOffer() {
		return offerDAO.getAllActiveOffers();
	}

	@Override
	public Set<Offer> getActiveOffer(Person person) {
		return offerDAO.getActiveOffers(person);
	}

	@Override
	public Set<Offer> getActiveOffer(Bike bike) {
		return offerDAO.getActiveOffers(bike);
	}

	@Override
	public Offer getOfferById(Integer id) {
		return offerDAO.getById(id);
	}

	@Override
	public Boolean acceptOffer(Integer id, Person person) {
		Offer o = offerDAO.getById(id);
		Person buyer = o.getPerson();
		if(buyer.getID().equals(person.getID())) {
			//dont let an employee accept their own offer
			return false;
		} else {
			o.accept();
			offerDAO.update(o);
			
			//the offer is accepted, the rest for this bike must be rejected
			offerDAO.rejectAll(o.getBike());
			
			return true;
		}
	}

	@Override
	public void rejectOffer(Integer id) {
		Offer o = offerDAO.getById(id);
		o.reject();
		offerDAO.update(o);
	}

	@Override
	public Set<Offer> getOffers(Person person) {
		return offerDAO.getOffers(person);
	}

	@Override
	public Set<Offer> getAllOffers() {
		return offerDAO.getAllOffers();
	}

	@Override
	public Set<Offer> getAcceptedOffer(Person person) {
		return offerDAO.getAcceptedOffers(person);
	}

	@Override
	public Set<Offer> getAllAcceptedOffers() {
		return offerDAO.getAllAcceptedOffers();
	}

}
