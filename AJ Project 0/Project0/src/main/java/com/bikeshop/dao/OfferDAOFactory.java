package com.bikeshop.dao;

public class OfferDAOFactory {
	public OfferDAO getOfferDAO() {
		return new OfferPostgres();
	}

}
