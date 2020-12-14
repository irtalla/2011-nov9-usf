package com.revature.services;

import com.revature.beans.Individual;
import com.revature.data.IndividualDAO;
import com.revature.data.IndividualDAOFactory;

public class IndividualServiceImpl implements IndividualService {
	
	IndividualDAOFactory individualDaoFactory = new IndividualDAOFactory();
	IndividualDAO individualDao = individualDaoFactory.getIndividualDAO();

	
	@Override
	public Integer addIndividual(Individual p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual getIndividualById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual getIndividualByUsername(String username) {
		return individualDao.getByUsername(username);
	}

	@Override
	public void updateIndividual(Individual p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteIndividual(Individual p) {
		// TODO Auto-generated method stub

	}

}
