package com.bikeshop.services;

import java.util.List;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Person;

public interface BikeService {

	public int addBike(Bike b);
	public boolean delBike(int id);
	public void viewOffers() ;
	public boolean declineOffer();
	public boolean acceptOffer();
	public int calcPay();
	public Set<Bike> getPayments();
	public Bike getByID(int id);
	
	public void updateStock();
	public List<Bike> getByOwner(Integer id);
	public boolean updateBike(Bike b);
	public boolean makePayment(Person p, Bike b);
	public List<Bike> viewUserBikes(Person user);
	public void purchaseBike(Person p, int id);
	public Bike newBike();
	public boolean editBike(int id);
}
