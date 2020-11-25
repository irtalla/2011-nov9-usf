package com.revature.data;

public class OfferDAOFactory {

	public OfferDAO getOfferDAO() {
        
   //     return new BikeCollections();
        return new OfferPostgres();
    }
}
	

