package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.data.DAOFactory;
import com.revature.data.OfferDAO;

public class OfferServiceImpl implements OfferService {

	private OfferDAO dao = DAOFactory.getOfferDAO();
	
	@Override
	public Integer addOffer(Offer o) {
		return dao.add(o).getID();
	}

	@Override
	public Offer getOfferById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<Offer> getOffers() {
		return dao.getAll();
	}

	@Override
	public void updateOffer(Offer o) {
		dao.update(o);

	}

	@Override
	public void removeOffer(Offer o) {
		dao.delete(o);

	}

	@Override
	public void acceptOffer(Offer o) {
		dao.AcceptOffer(o);
		
	}

	@Override
	public void rejectOffer(Offer o) {
		dao.RejectOffer(o);
		
	}

}
