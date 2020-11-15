package com.revature.dao;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;

public class OfferCollections implements OfferDAO{
	private static Set<Offer> allOffers;
	
	public OfferCollections() {
		allOffers = new HashSet<Offer>();
	}

	@Override
	public Set<Offer> getAllOffers() {
		return allOffers;
	}
	
	
}
