package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.exceptions.NonUniqueUsernameException;

public interface OfferService {
	public Integer addOffer(Offer o) throws NullPointerException, NonUniqueUsernameException;
	public Offer getOfferById(Integer id) throws NullPointerException;
	public Set<Offer> getAllOffers();
	public void updateOffer(Offer o);
	public void deleteOffer(Offer o);
	void acceptOffer(Integer id);
}
