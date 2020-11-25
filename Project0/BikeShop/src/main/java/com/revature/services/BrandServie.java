package com.revature.services;

import java.util.Set;

import com.revature.beans.Brand;

public interface BrandServie {
	public Integer addBrand(Brand b);
	public Brand getBrandById(Integer id);
	public Set<Brand> getBrands();
	public Set<Brand> getAvailableBrands();
	public void updateBrand(Brand b);
	public void removeBrand(Brand b);
}
