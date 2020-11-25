package services;

import java.util.Set;

import models.Bike;
import models.Offer;
import models.User;

public interface OfferService {
public Integer addOffer(Offer o);
public Offer getOfferById(Integer i);
public Set<Offer> getOffersByUserId(Integer i);
public Set<Offer> getAllOffers();
public void updateOffer(Offer o);
public void removeOffer(Offer o);
public void MassUpdate(Integer i, Integer x);

}
