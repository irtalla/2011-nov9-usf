package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	public Offer add(Offer t);
	public Set<Offer> getOffersForBike(Bike b);
	public void acceptOffer(Offer t);
}