package com.revature.data;

/*
 * This is an instance of the abstract factory design pattern. Notice
 * that its return type is an abstract ProductDAO, but its return types
 * are concrete DAOs that implement the ProductDAO. In this case, the
 * returned concrete DAO depends on the mode, which can be "dev" for a 
 * mock database, or "prod" for a live postgres database. This is what
 * we import and call in the application, NOT any concrete DAO class ! 
 * 
 * Notice all that this factory relies no a default constructor, since its
 * only job is to return an implementation of an abstract surface
 */
public class ProductDAOFactory {
    
    public ProductDAO getProductDAO(String mode) throws Exception {
        
    	
    	switch (mode) {
    		case "dev": return new ProductCollections();
    		case "prod": return new ProductPostgres();
    		default: throw new Exception(); 
    	
    	}
    }
    
    
}
