package dev.elliman.data;

public class BikeDAOFactory {
	public BikeDAO getBikeDAO() {
		return new BikePostgres();
	}
}
