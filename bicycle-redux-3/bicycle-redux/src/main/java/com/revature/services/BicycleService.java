package com.revature.services;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.data.BicycleDAO;
import com.revature.data.BicycleDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;

import java.util.List;

public class BicycleService {
    BicycleDAO bicycleDao;
    OfferDAO offerDao;

    public BicycleService() {
        this.bicycleDao = new BicycleDAOFactory().getBicycleDAO();
        this.offerDao = new OfferDAOFactory().getOfferDAO();
    }

    public List<Bicycle> getAllBicycles() {
        return this.bicycleDao.getAllBicycles();
    }

    public List<Bicycle> getBicyclesForUser(String userName) {
        return this.bicycleDao.getBicyclesForUser(userName);

    }

    public Bicycle addBicycle(Integer id, String name, String owner) {
        return this.bicycleDao.addBicycle(id, name, owner);
    }

    public boolean removeBicycle(Integer bicycleId) {
        return this.bicycleDao.removeBicycle(bicycleId);
    }

    public void selectAndAcceptOffer(Integer offerId, Integer bicycleId) {
        this.offerDao.acceptOffer(offerId, bicycleId);
        String offerOwner =this.offerDao.getOfferOwner(offerId);
        this.bicycleDao.updateOwner(bicycleId, offerOwner);
    }

    public boolean addOffer(Offer offer) {
        return this.offerDao.addOffer(offer);
    }

    public List<Offer> getOffersForBicycle(Integer bicycleId) {
        return this.offerDao.getOffersForBicycle(bicycleId);
    }

    public List<Offer> getCompleteOffers() {
        return this.offerDao.getCompleteOffers();
    }

    public List<Offer> getOffersForUser(String userName) {
        return this.offerDao.getOffersForUser(userName);
    }
}
