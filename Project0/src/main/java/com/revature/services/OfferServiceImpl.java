package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;


public class OfferServiceImpl implements OfferService {

	@Override
	public Set<Offer> getOffers() {
		System.out.println("Called getOffers() ");
		return null;
	}

	@Override
	public Set<Offer> getOffersByProductId(Integer productId) {
		System.out.println("Called getOffersByProductId() ");
		return null;
	}

	@Override
	public boolean acceptOffer(Integer offerId) {
		System.out.println("Called acceptOffer() ");
		return false;
	}

	@Override
	public boolean rejectOffer(Integer offerId) {
		System.out.println("Called rejectOffer() ");
		return false;
	}

}
