package com.revature.services;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;

public interface EmployeeService {

	public boolean addABicycle(Bicycle bicycle);
	public Offer acceptAnOffer(Offer offer);
	public Offer rejectAnOffer(Offer offer);
}
