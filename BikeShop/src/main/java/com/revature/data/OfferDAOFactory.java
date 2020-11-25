package com.revature.data;

public class OfferDAOFactory {
    
    public OfferDAO getOfferDAO() {
        
        return new OfferPostgres();
    }
}