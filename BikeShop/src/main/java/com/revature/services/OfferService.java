package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;

public interface OfferService {
	
	public Integer addOffer(Offer o);
	public Offer getOfferById(Integer id);
	public Set<Offer> getOffers();
	public Set<Offer> getOffersForBike(Bike b);
	public void updateOffer(Offer o);
	public void acceptOffer(Offer o);
	public void removeOffer(Offer o);
}
