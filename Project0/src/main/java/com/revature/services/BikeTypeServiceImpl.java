package com.revature.services;

import java.util.Set;

import com.revature.beans.BikeType;
import com.revature.data.BikeTypeDAO;
import com.revature.data.BikeTypePostgres;

public class BikeTypeServiceImpl implements BikeTypeService {
	private BikeTypeDAO BTDao = new BikeTypePostgres();
	

	@Override
	public Integer addBikeType(BikeType bt) throws NullPointerException {
		return BTDao.add(bt).getId();
	}

	@Override
	public BikeType getBikeTypeById(Integer id) throws NullPointerException {
		return BTDao.getById(id);
	}

	@Override
	public BikeType getBikeTypeByName(String name) throws NullPointerException {
		return BTDao.getBikeTypeByName(name);
	}

	@Override
	public Set<BikeType> getBikeTypes() {
		return BTDao.getAll();
	}

	@Override
	public void updateBikeType(BikeType bt) {
		BTDao.update(bt);

	}

	@Override
	public void deleteBikeType(BikeType bt) {
		BTDao.delete(bt);
	}

}
