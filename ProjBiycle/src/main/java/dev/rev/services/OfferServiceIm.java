package dev.rev.services;

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
	public Offer getofferbyPersonID(int id) {
		// TODO Auto-generated method stub
		return null;
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

}
