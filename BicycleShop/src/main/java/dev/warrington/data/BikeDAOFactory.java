package dev.warrington.data;

public class BikeDAOFactory {

	public BikeDAO getBikeDAO() {
		
		return new BikePostgres();
		
	}
	
}