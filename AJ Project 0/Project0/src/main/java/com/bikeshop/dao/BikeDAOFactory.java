package com.bikeshop.dao;

public class BikeDAOFactory {
	public BikeDAO getBikeDAO() {
		
		return new BikePostges();
	}

}
