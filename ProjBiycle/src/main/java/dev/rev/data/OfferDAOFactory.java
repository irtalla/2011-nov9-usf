package dev.rev.data;

public class OfferDAOFactory {

public OfferDAO getOfferDAO() {
        
        return new OfferPostgre();
    }
}
