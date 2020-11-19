package com.revature.data;

import java.util.Set;

/*
 * This is the actual Postgres abstraction layer. This will interface
 * directly with the database 
 */

import com.revature.beans.Product;

public class ProductPostgres implements ProductDAO {

	@Override
	public Product add(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Product> getAvailableProducts() {
		return null;
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub

	}

}
