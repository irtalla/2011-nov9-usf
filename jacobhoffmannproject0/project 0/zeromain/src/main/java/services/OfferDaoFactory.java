package services;

import data.OfferDao;
import data.OfferPostgres;

public class OfferDaoFactory {
	 public OfferDao getOfferDAO() {
	        
	        return new OfferPostgres();
	    }
}
