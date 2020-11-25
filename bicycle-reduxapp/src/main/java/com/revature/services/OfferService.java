package com.revature.services;

import java.util.List;

import com.revature.beans.Offer;

public interface OfferService {
    public String acceptOffer(Integer offerId, Integer bicycleId) ;

    public boolean addOffer(Offer offer);

    public List<Offer> getOffersForBicycle(Integer bicycleId);

    List<Offer> getCompleteOffers();
    public List<Offer> getOffersForUser(String userName);
    public List<Offer> rejectOffer(String userName);
    public List<Offer> rejectAllOffer(String userName);
}
