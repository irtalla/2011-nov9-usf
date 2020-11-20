package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Product;

/*
 * This class implements ProductDAO, but does so locally. There is
 * no database access here. This call can be used to mock database 
 * access operations
 */

public class ProductCollections implements ProductDAO {
	private static Set<Product> Products;
	
	public ProductCollections() {
	
		
		// TODO : instantiate three product objects like below 
		
//		Products = new HashSet<Product>();
//		Product c = new Product();
//		c.setId(1);
//		c.setName("Fluffy");
//		c.setPrice(3);
//		Breed b = new Breed();
//		b.setId(1);
//		b.setName("Persian");
//		c.setBreed(b);
//		Status s = new Status();
//		s.setId(1);
//		s.setName("Available");
//		c.setStatus(s);
//		Products.add(c);
//		
//		c = new Product();
//		c.setId(2);
//		c.setName("Lucky");
//		c.setPrice(7);
//		b = new Breed();
//		b.setId(2);
//		b.setName("Domestic Shorthair");
//		c.setBreed(b);
//		s = new Status();
//		s.setId(1);
//		s.setName("Available");
//		c.setStatus(s);
//		Products.add(c);
//		
//		c = new Product();
//		c.setId(3);
//		c.setName("Howard");
//		c.setPrice(1);
//		b.setId(3);
//		b.setName("Calico");
//		c.setBreed(b);
//		s = new Status();
//		s.setId(1);
//		s.setName("Available");
//		Products.add(c);
	}

	@Override
    public Product add(Product t) {
		// TODO update id
		Products.add(t);
		return t;
    }
	
	@Override
	public Product getById(Integer id) {
		for(Product Product : Products) {
            if(Product.getId().equals(id)) {
                return Product;
            }
        }
		return null;
	}
    
    @Override
    public Set<Product> getAll() {
        return Products;
    }
    
    @Override
    public Set<Product> getAvailableProducts() {
        Set<Product> aProducts = new HashSet<>();
        for(Product Product : Products) {
        	// TODO needed to get the name from status
            if( Product.getStatus().getName().equalsIgnoreCase("available") ) {
                aProducts.add(Product);
            }
        }
        return aProducts;
    }
    
    @Override
    public void update(Product t) {
    	
    	if ( t == null ) { return; }
    	
    	for (Product Product : Products ) {
    		if ( t.getId().equals(Product.getId()) ) {
    			Products.remove(Product); 
    			Products.add(t); 
    		}
    	}
    }
    
    @Override
    public void delete(Product t) {
        if(Products.contains(t)) {
            Products.remove(t);
        }
    }



}
