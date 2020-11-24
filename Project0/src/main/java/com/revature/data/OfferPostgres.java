package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu;
	
	{
		cu = ConnectionUtil.getConnectionUtil();
	}
	@Override
	public Offer add(Offer t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Offer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectAll(Bike bike) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Offer> getOffers(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffers(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAcceptedOffers(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getOffers(Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffers(Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAllOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAllActiveOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAllAcceptedOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer update(Offer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffersMadeByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAcceptedOffersMadeByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getActiveOffersForBike(Bike b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAllOffersForBike(Bike b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getAcceptedOfferForBike(Bike b) {
		// TODO Auto-generated method stub
		return null;
	}

}
