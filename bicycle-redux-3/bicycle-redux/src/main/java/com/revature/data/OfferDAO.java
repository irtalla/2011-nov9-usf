package com.revature.data;

import com.revature.beans.Offer;

import java.util.List;

public interface OfferDAO {
    boolean acceptOffer(Integer offerId, Integer bicycleId);
    boolean addOffer(Offer offer);
    List<Offer> getOffersForBicycle(Integer bicycleId);
    List<Offer> getCompleteOffers();
    List<Offer> getOffersForUser(String userName);
    String getOfferOwner(Integer offerId);
}
