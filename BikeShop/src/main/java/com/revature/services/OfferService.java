package com.revature.services;

import java.util.Set;


import com.revature.beans.Offer;

public interface OfferService {
	public Integer addOffer(Offer o);
	// "read" methods
	public Offer getOfferById(Integer id);
	public Set<Offer> getOffers();
	// "update" methods
	public void updateOffer(Offer o);
	public void acceptOffer(Offer o);
	public void rejectOffer(Offer o);
	// "delete" methods
	public void removeOffer(Offer o);
}
