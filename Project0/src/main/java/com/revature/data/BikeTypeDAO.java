package com.revature.data;

import com.revature.beans.BikeType;

public interface BikeTypeDAO extends GenericDAO<BikeType> {
	public BikeType add(BikeType bt);
	public BikeType getBikeTypeByName(String name);
}
