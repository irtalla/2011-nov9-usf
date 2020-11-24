package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.beans.Product;
import com.revature.beans.Person;

/*
 * This is the interface we want to program to. 
 */

public interface ProductService {
	// "create" method: returns the unique identifier of the added Product
	public Product addProduct(Product c);
	// "read" methods
	public Product getProductById(Integer Id);
	public Set<Product> getProductsByOwnerId(Integer customerId);
	public Set<Product> getProducts();
	public Set<Product> getAvailableProducts();
	// "update" methods
	public void updateProduct(Product c);
	public void setOwnerForProduct(Person p, Product c);
	// "delete" methods
	public void removeProduct(Integer productId);
	public void getRemainingPayments(Integer productId); 
}
