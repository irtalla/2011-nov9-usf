package dev.warrington.data;

public class OfferDAOFactory {
	
	public OfferDAO getOfferDao() {
		
		return new OfferPostgres();
		
	}

}
