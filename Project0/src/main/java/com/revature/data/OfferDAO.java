package com.revature.data;

import java.util.Set;

import com.revature.beans.Offer;


public interface OfferDAO extends GenericDAO<Offer> {

	public Offer add(Offer o);
	public Set<Offer> getAvailableOffers();
}
