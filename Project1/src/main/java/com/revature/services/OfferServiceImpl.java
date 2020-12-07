package com.revature.services;

import java.util.Set;

import com.cross.beans.Offer;
import com.cross.beans.Pitch;
import com.cross.beans.Purchase;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchasePostgres;


public class OfferServiceImpl implements OfferService {
	
	private OfferDAO offerDao;
	private PurchaseDAO purchaseDao;
	
	public OfferServiceImpl() {
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		purchaseDao = new PurchasePostgres(); 
	}
	
	
	@Override
	public Offer add(Offer newOffer) {
		return offerDao.add(newOffer); 
	}
	
	
	@Override
	public Set<Offer> getAll() {
		return offerDao.getAll(); 
	}

	@Override
	public Set<Offer> getOffersByProductId(Integer productId) {
		System.out.println("Called getOffersByProductId() ");
		return null;
	}

	@Override
	public boolean acceptOffer(Integer offerId) {
		
		Offer acceptedOffer = offerDao.getById(offerId); 
		Purchase newPurchase = new Purchase(); 
		newPurchase.setCustomerId( acceptedOffer.getCustomerId() );
		newPurchase.setProductId( acceptedOffer.getProductId() );
		return purchaseDao.add(newPurchase) == null;
	}

	@Override
	public boolean rejectOffer(Integer offerId) {
		
		Offer rejectedOffer = offerDao.getById(offerId); 
		rejectedOffer.getStatus().setId(5);
		rejectedOffer.getStatus().setName("rejected");
		return offerDao.update(rejectedOffer);
	}


	@Override
	public Set<Offer> getAllAcceptedOffers() {
		Set<Offer> offers = getAll(); 
		offers.removeIf( offer -> ! offer.getStatus().getName().equalsIgnoreCase("ACCEPTED") );
		return offers; 
	}

}
