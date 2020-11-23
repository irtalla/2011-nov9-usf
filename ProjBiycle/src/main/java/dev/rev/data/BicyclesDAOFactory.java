package dev.rev.data;

public class BicyclesDAOFactory {

	public BicycleDAO getBicycleDAO() {
        
        return new BicyclePostgre();
    }
}