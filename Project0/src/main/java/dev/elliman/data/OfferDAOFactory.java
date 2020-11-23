package dev.elliman.data;

public class OfferDAOFactory {
	public OfferDAO getOfferDAO() {
		return new OfferPostgres();
	}
}
