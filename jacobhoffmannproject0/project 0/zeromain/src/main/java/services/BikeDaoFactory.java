package services;

import data.BikeDao;
import data.BikePostgres;

public class BikeDaoFactory {
	 public BikeDao getBikeDAO() {
	        
	        return new BikePostgres();
	    }
}
