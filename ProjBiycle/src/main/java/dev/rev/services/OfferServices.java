package dev.rev.services;

import java.util.Set;

import dev.rev.model.Offer;

public interface OfferServices {
	
	public int putOffer(Offer Offer) throws Exception;
	public Set<Offer> getofferbyPersonID(int id);
	public 	Offer	getoffersonBike(int bike_id);
	public void deleteOffer(int Offer_id);
	public void updateOffer(Offer offer);
	public Set<Offer> getAll();
	public String accept_reject_offer(int i);
	public void rejectothers(int id);
	public int bike_id_byofferid(int id);
	
}
