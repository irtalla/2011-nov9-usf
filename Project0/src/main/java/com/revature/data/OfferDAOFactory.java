package com.revature.data;

public class OfferDAOFactory {
	
	public OfferDAO getOfferDao() {
		return new OfferPostgres();
	}
	
}
