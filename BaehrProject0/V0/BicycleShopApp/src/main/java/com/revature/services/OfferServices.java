package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;

public interface OfferServices {
	
	public Set<Offer>  getAll();

	public Set<Offer>  getOutstanding();
	
	public Set<Offer> getByUserID(Integer id); //returns all offers made by a particular user
	
	public void addOffer(Offer o);//create
	
	public Offer getOfferByOfferId(Integer id);//read
	
	public void updateOffer(Offer o);//update
	
	public void delete(Offer o);//delete
	
	public Offer acceptOffer(Offer o);//updates all offers for that particular bike and creates a purchase

	

}
