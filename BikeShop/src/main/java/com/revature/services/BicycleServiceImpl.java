package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.data.BikeDAO;
import com.revature.data.DAOFactory;

public class BicycleServiceImpl implements BicycleService {
	private BikeDAO dao = DAOFactory.getBikeDAO();
	@Override
	public Integer addBicycle(Bicycle b) {
		return dao.add(b).getID();
	}

	@Override
	public Bicycle getBicycleById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<Bicycle> getBicycles() {
		return dao.getAll();
	}

	@Override
	public void updateBicycle(Bicycle b) {
		dao.update(b);

	}

	@Override
	public void removeBicycle(Bicycle b) {
		dao.delete(b);

	}

}
