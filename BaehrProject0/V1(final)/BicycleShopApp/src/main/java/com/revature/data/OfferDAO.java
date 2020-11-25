package com.revature.data;

import java.util.Set;

import com.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer>{
	
	public Offer add(Offer o);
	public Set<Offer> getAll();
	public Set<Offer> getOutstanding();
	public Set<Offer> getByUserId(Integer id);
	public void update(Offer o);
	public Offer acceptOffer(Offer o);
	public Set<Offer> getByBicycleId(Integer id);
	//public void rejectOtherOffers(Offer o);
}
