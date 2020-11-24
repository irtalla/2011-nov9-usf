package dev.rev.data;

import java.util.Set;

import dev.rev.model.Offer;

public interface OfferDAO extends GenericDAo<Offer>{
	
	public String accept_reject_offer(int id);
	public Set<Offer> getpersonOffer(int id);
	public void rejectothers(int id);
	public int bike_id_byofferid(int id);
	public void rejectOffer(int id);
	public int getpersonId(int id,int ofer_id);


}
