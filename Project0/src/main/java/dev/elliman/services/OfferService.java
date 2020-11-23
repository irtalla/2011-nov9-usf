package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;

public interface OfferService {
		//create
		public Integer makeOffer(Offer offer);
		
		//read
		public Set<Offer> getActiveOffer();
		public Set<Offer> getActiveOffer(Person person);
		public Set<Offer> getOffers(Person person);
		public Set<Offer> getActiveOffer(Bike bike);
		public Offer getOfferById(Integer id);
		
		//update
		public Boolean acceptOffer(Integer id, Person person);
		public void rejectOffer(Integer id);
}
