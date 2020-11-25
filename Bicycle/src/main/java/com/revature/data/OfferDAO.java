package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer>{
	public Offer add(Offer o);
	public Set<Offer> getAvailableOffers();
	public Set<Offer> getAcceptedOffers();
	public void accept(Offer o, Bike b);
	public void reject(Offer o, Bike b);
	public void deleteOfferForBikeId(Integer id);

}
