package com.revature.services;

import java.util.Set;

import com.revature.models.Offer;

public interface OfferService {
	public Integer addOffer(Offer o);
	public Offer getOfferById(Integer id);
	public Set<Offer> getOffers();
	public Set<Offer> getOfferByCustomer(Integer id);
	public Set<Offer> getOfferByEmployee(Integer id);
	public Set<Offer> getOfferByStatus(String status);
	public void updateOffer(Offer o);
	public void removeOffer(Offer o);
}
