package com.revature.data;

import java.util.Set;

import com.revature.beans.Brand;

public interface BrandDAO extends GenericDAO <Brand>{
	public Brand add(Brand b);
	public Set<Brand> getAvailableBrands();
}
