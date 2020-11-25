package com.revature.data;

public class BicycleDAOFactory {
    
    public static BicycleDao getBicycleDAO() {
        
        return new BicycleDao();
    }

}
