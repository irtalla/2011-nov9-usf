package com.revature.dao;

import java.util.Set;

import com.revature.beans.Offer;

public interface OfferDAO {
	public Set<Offer> getAllOffers();
	public boolean addAnOffer(Offer o);
	public boolean removeAnOffer(Offer o);
	public void update(Offer o);
}
