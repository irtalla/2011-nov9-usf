package com.revature.data;

/*
 * This is an instance of the abstract factory design pattern. Notice
 * that its return type is an abstract ProductDAO, but its return types
 * are concrete DAOs that implement the ProductDAO.
 * 
 * Notice all that this factory relies no a default constructor, since its
 * only job is to return an implementation of an abstract surface
 */
public class ProductDAOFactory {
    
    public ProductDAO getProductDAO() {
        return new ProductPostgres();

    }
    
    
}
