package com.bikeshop.services;

import java.util.List;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.dao.BikeDAO;
import com.bikeshop.dao.BikeDAOFactory;

public class AllServiceImpl implements AllService{
	private BikeDAO bd;
	
	public AllServiceImpl() {
		BikeDAOFactory bdf = new BikeDAOFactory();
		bd = bdf.getBikeDAO();
	}
	public List<Bike> getInventory() {
		
		return bd.getInventory() ;
		
		
	}

}
