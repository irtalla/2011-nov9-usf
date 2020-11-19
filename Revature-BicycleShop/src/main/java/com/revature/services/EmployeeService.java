package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;

public interface EmployeeService {

	public boolean addABicycle(Bicycle bicycle);
	public boolean removeABicycle(Bicycle bicycle);
	public Offer acceptAnOffer(Offer offer);
	public Offer rejectAnOffer(Offer offer);
	public Set<Offer> getAllOffers();
	public Set<Bicycle> getAllBicycles();
}
