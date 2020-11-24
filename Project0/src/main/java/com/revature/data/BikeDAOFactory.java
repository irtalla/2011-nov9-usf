package com.revature.data;

public class BikeDAOFactory {
    
    public BikeDAO getCatDAO() {
        
        return new BikePostgres();
    }
}
