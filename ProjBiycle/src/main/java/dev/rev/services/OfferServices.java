package dev.rev.services;

import dev.rev.model.Offer;

public interface OfferServices {
	
	public int putOffer(Offer Offer) throws Exception;
	public Offer getofferbyPersonID(int id);
	public 	Offer	getoffersonBike(int bike_id);
	public void deleteOffer(int Offer_id);
	public void updateOffer(Offer offer);
	
}
