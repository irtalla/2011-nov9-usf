package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferService {
	public Offer makeOffer(Offer offer);
	
	public Set<Offer> getActiveOffersMadeByPerson(Person p);
	
	public Set<Offer> getActiveOffersForBike(Bike b);
	
	public Offer getAcceptedOfferForBike(Bike b);
	
	Offer acceptOffer(Offer t);

	Offer rejectOffer(Offer t);
	
	public Offer getOfferById(Integer id);

	public void deleteOffer(Offer updatedOffer1);

	public Set<Offer> getAll();

	public Offer addOffer(Offer offer1) throws Exception;

	public Set<Offer> getAllActiveOffers();
}
