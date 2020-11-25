package data;
import java.util.Set;

import models.Bike;
import models.Offer;
import models.User;

public interface OfferDao extends GenericDAO<Offer>{
	public Set<Offer> getByUserId(Integer Id);
	public Offer add(Offer o);
	public void MassUpdate(Integer bikeId, Integer OfferId);
}
