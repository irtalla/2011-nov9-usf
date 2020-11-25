package com.bikeshop.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Model;
import com.bikeshop.beans.Offer;

public class BikeCollection implements BikeDAO {
	private List<Bike> bikes;
	
	public BikeCollection() {
		bikes = new ArrayList<>();
		
		Bike b = new Bike();
		b.setId(1);
		b.setManufacturer("Huffy");
		b.setModel("3");
		b.setStatus("In Stock");
		
		bikes.add(b);
	}

		
	public Set<Offer> viewOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addBike(Bike bike) {
		// TODO Auto-generated method stub
		bikes.add(bike);
		return bike.getId();
	}

	public boolean delBike(Bike bike) {
		bikes.remove(bike);
		return true;
	}

	public boolean declineOffer(Offer t) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean acceptOffer(Offer t) {
		// TODO Auto-generated method stub
		return true;
	}

	public int getPayment() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int setPayment() {
		return 0;
	}

	public void updateBike1(Bike bike) {
		

	}

	public List<Bike> getInventory() {
		
		return bikes;
	}


	@Override
	public Bike getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean setPayment(Bike b) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateBike(Bike b) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Bike> getByOwner(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
