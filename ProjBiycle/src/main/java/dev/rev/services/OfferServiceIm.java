package dev.rev.services;

import java.util.Set;

import dev.rev.data.OfferDAO;
import dev.rev.data.OfferDAOFactory;
import dev.rev.model.Offer;

public class OfferServiceIm implements OfferServices{
	
	OfferDAO ofDAO;
	
	public OfferServiceIm() {
		OfferDAOFactory odf=new OfferDAOFactory();
		ofDAO=odf.getOfferDAO();
		
		
	}
	@Override
	public int putOffer(Offer offer) throws Exception {
		
		return ofDAO.add(offer).getOffer_id();
	}

	@Override
	public Set<Offer> getofferbyPersonID(int id) {
		// TODO Auto-generated method stub
		return ofDAO.getpersonOffer(id);
	}

	@Override
	public Offer getoffersonBike(int bike_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOffer(int Offer_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOffer(Offer offer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Set<Offer> getAll() {
		return ofDAO.getAll();
	}
	@Override
	public String accept_reject_offer(int i) {
		// TODO Auto-generated method stub
		return ofDAO.accept_reject_offer(i);
		
	}
	@Override
	public void rejectothers(int id) {
		// TODO Auto-generated method stub
		ofDAO.rejectothers(id);
		
	}
	@Override
	public int bike_id_byofferid(int id) {
		// TODO Auto-generated method stub
		return ofDAO.bike_id_byofferid(id);
	}
	@Override
	public void rejectOffer(int id) {
		// TODO Auto-generated method stub
		ofDAO.rejectOffer(id);
	}
	@Override
	public int getpersonId(int bicycle_id,int ofer_id) {
		// TODO Auto-generated method stub
		return ofDAO.getpersonId(bicycle_id,ofer_id);
	}
	

}
