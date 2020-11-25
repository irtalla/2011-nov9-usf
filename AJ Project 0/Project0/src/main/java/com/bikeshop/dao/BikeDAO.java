package com.bikeshop.dao;
import java.util.List;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Model;
import com.bikeshop.beans.Offer;

public interface BikeDAO {	
	public Set<Offer> viewOffers() ;
	
	
	public int addBike(Bike bike);
	public boolean delBike(Bike bike);
	public Bike getByID(int id);
	public int getPayment();
//	public boolean setPayment();
	public boolean setPayment(Bike b);

	public boolean updateBike(Bike b);
	public List<Bike> getInventory();


	public List<Bike> getByOwner(Integer id);



}
