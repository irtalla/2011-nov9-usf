package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Finance;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.data.BicyclePostgres;
import com.revature.data.FinancePostgres;
import com.revature.data.OfferPostgres;

public class OfferService {
	private OfferPostgres offerPostgres;
	private BicyclePostgres bicyclePostgres;
	private FinancePostgres financePostgres;
	
	public OfferService() {
		offerPostgres = new OfferPostgres();
		bicyclePostgres = new BicyclePostgres();
		financePostgres = new FinancePostgres();
	}
	
	public Integer addOffer(Offer t) {
		return offerPostgres.add(t).getId();
	}
	
	public Offer getByInt(Integer id) {
		return offerPostgres.getById(id);
	}
	
	public Set<Offer> getAll(){
		return offerPostgres.getAll();
	}
	
	public void update(Offer t) {
		offerPostgres.update(t);
	}
	
	public void delete(Offer t) {
		offerPostgres.delete(t);
	}
	
	public Set<Offer> getOfferByBicycle(Bicycle b){
		return offerPostgres.getOfferByBicycle(b);
	}
	
	public Set<Offer> getOfferByPerson(Person p){
		return offerPostgres.getOfferByPerson(p);
	}
	
	public void seletedOffer(Offer offer) {
		Set<Offer> offers = offerPostgres.getOfferByBicycle(offer.getBicycle());
		for (Offer xOffer : offers) {
			if (xOffer != offer) {
				offerPostgres.delete(xOffer);
			}
		}
		bicyclePostgres.updateOwner(offer.getBicycle(), offer.getPerson());
		Finance finance = new Finance();
		finance.setBicycle(offer.getBicycle());
		finance.setFinancedAmount(offer.getPrice());
		finance.setPaidAmount(0.00);
		financePostgres.add(finance);
	}
}
