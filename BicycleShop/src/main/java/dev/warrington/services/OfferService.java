package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Offer;

public interface OfferService {

	public Integer addOffer(Offer o);
	
	public Set<Offer> getAllOffers();
	
	public Set<Offer> getMyOffers(Integer id);
	
	public void deleteOffer(Offer o);
	
	public void deleteGroup(Integer id);
	
}