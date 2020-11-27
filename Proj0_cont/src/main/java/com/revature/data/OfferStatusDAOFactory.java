package com.revature.data;

public class OfferStatusDAOFactory {
	public OfferStatusDAO getOfferStatusDao() {
		return new OfferStatusPostgres();
	}
}
