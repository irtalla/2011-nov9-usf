package com.revature.services;

import java.util.Set;

import com.revature.beans.BikeType;

public interface BikeTypeService {
	public Integer addBikeType(BikeType bt) throws NullPointerException;
	public BikeType getBikeTypeById(Integer id) throws NullPointerException;
	public BikeType getBikeTypeByName(String name) throws NullPointerException;
	public Set<BikeType> getBikeTypes();
	public void updateBikeType(BikeType bt);
	public void deleteBikeType(BikeType bt);
}
