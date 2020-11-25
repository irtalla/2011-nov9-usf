package services;

import java.util.Set;

import data.OfferDao;
import models.Bike;
import models.Offer;
import models.User;

public class OfferServiceImpl implements OfferService{
	private OfferDao offerdao;
	public OfferServiceImpl() {
		OfferDaoFactory offerDaoFactory = new OfferDaoFactory();
		offerdao = offerDaoFactory.getOfferDAO();
	}
	@Override
	public Integer addOffer(Offer o) {
		//try {
		//System.out.println("hi");
			return offerdao.add(o).getOfferId();
		//} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			//return null;
	//	}
	}
	@Override
	public Offer getOfferById(Integer i) {
		return offerdao.getById(i);
	}
	@Override
	public Set<Offer> getOffersByUserId(Integer i) {
		return offerdao.getByUserId(i);
	}
	@Override
	public Set<Offer> getAllOffers() {
		return offerdao.getAll();
	}
	@Override
	public void updateOffer(Offer o) {
		offerdao.update(o);
		
	}
	@Override
	public void removeOffer(Offer o) {
		offerdao.delete(o);
		
	}
	@Override
	public void MassUpdate(Integer i, Integer x) {
			offerdao.MassUpdate(i, x);
	}
}
