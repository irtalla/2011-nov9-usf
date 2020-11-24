package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferService {
	public Offer makeOffer(Offer offer);
	
	//read
	public Set<Offer> getActiveOffersMadeByPerson(Person p);
	
	public Set<Offer> getActiveOffersForBike(Bike b);
	
	public Offer getAcceptedOfferForBike(Bike b);
	
	public void acceptOfferForBike(Bike b, Offer o);
	
	public void rejectOfferForBike(Bike b, Offer o);
	
	public Offer getOfferById(Integer id);

	public void deleteOffer(Offer updatedOffer1);

	public Set<Offer> getAll();

	public Offer addOffer(Offer offer1);
}
