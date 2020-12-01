package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.beans.Person;

/*
 * This is the interface we want to program to. 
 */

public interface ProductService {
	// "create" method: returns the unique identifier of the added Product
	public Pitch addProduct(Pitch c);
	// "read" methods
	public Pitch getProductById(Integer Id);
	public Set<Pitch> getProductByCategory(String userChoice); 
	public Set<Pitch> getProductsByOwnerId(Integer customerId);
	public Set<Pitch> getProducts();
	public Set<Pitch> getAvailableProducts();
	// "update" methods
	public void updateProduct(Pitch c);
	// "delete" methods
	public void removeProduct(Integer productId);
	public void getRemainingPayments(Integer productId);
}
