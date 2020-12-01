package com.revature.data;

import java.util.Set;
import com.revature.beans.Pitch;

/*
 * The ProductDAO extends the GenericDAO by providing a method
 * to see all available products. Again, this is just an interface
 * specification, not an implementation 
 */
public interface ProductDAO extends GenericDAO<Pitch> {
	public Set<Pitch> getAvailableProducts();
}
