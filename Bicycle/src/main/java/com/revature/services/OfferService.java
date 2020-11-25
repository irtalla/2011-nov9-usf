package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferService {
	// "create" method: returns the unique identifier of the added Cat
	public Integer addOffer(Offer o);
	// "read" methods
	public Offer getOfferById(Integer id);
	public Set<Offer> getOffers();
	public Set<Offer> getAvailableOffers();
	// "update" methods
	public void updateOffer(Offer o);
	public void acceptOffer(Offer o, Bike b);
	// "delete" methods
	public void removeOffer(Offer o);
	public void removeAllOffersforBikeID(Integer id);
	public Set<Offer> getAcceptedOffers();
}
