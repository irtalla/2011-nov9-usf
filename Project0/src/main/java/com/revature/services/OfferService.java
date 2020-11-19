package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;


/**
 * In order to provide a more normalized database and more
 * independent data representations, we expose the OfferService, 
 * which abstract operations on offer objects
 */

public interface OfferService {
	
	public Set<Offer> getOffers(); 
	public Set<Offer> getOffersByProductId(Integer productId);
	public boolean acceptOffer(Integer offerId); 
	public boolean rejectOffer(Integer offerId);

}
