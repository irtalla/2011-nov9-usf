package com.revature.services;

import java.util.List;

import com.revature.beans.Offer;
import com.revature.data.OfferDao;

public class OfferServiceImpl implements OfferService {

    @Override
    public String acceptOffer(Integer offerId, Integer bicycleId) {
        // TODO Auto-generated method stub
        return OfferDao.acceptOffer(offerId, bicycleId);
    }

    @Override
    public boolean addOffer(Offer offer) {
        // TODO Auto-generated method stub
        return OfferDao.addOffer(offer);
    }

    @Override
    public List<Offer> getOffersForBicycle(Integer bicycleId) {
        // TODO Auto-generated method stub
        return OfferDao.getOffersForBicycle(bicycleId);
    }

    @Override
    public List<Offer> getCompleteOffers() {
        // TODO Auto-generated method stub
        return OfferDao.getCompleteOffers();
    }

    @Override
    public List<Offer> getOffersForUser(String userName) {
        // TODO Auto-generated method stub
        return OfferDao.getOffersForUser(userName);
    }

    @Override
    public List<Offer> rejectOffer(String userName) {
        // TODO Auto-generated method stub
        return OfferDao.rejectOffer(userName);
    }

    @Override
    public List<Offer> rejectAllOffer(String userName) {
        // TODO Auto-generated method stub
        return OfferDao.rejectAllOffer(userName);
    }

}
