package com.revature.data;

import java.util.Set;
import com.revature.beans.Product;

/*
 * The ProductDAO extends the GenericDAO by providing a method
 * to see all available products. Again, this is just an interface
 * specification, not an implementation 
 */
public interface ProductDAO extends GenericDAO<Product> {
	public Set<Product> getAvailableProducts();
}
