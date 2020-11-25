package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferService {
	// Create
	public Integer addOffer(Offer o, Bicycle b);
	//public Integer makeOffer(Bicycle b, Person p, Double amount);
	
	// Read
	public Offer getOfferById(Integer id);
	public Set<Offer> getOffers();
	public Set<Offer> getOffersByBicycle(Bicycle b);
	public Set<Offer> getOffersByPerson(Person p);
	
	// Update
	public void updateOffer(Offer o);
	public void acceptOffer(Offer o);
	public void rejectOffer(Offer o);
	
	// Delete
	public void deleteOffer(Offer o);
	
}
