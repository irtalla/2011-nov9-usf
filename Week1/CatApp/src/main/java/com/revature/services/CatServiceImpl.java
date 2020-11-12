package com.revature.services;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.data.CatDAO;
import com.revature.data.PersonDAO;

public class CatServiceImpl implements CatService {
	private CatDAO catDao;
	private PersonDAO personDao;
	
	public CatServiceImpl() {
		// CatDAOFactory catDaoFactory = new CatDAOFactory();
		// catDao = catDaoFactory.getCatDao();
	}

	@Override
	public Integer addCat(Cat c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat getCatById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cat> getCats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cat> getAvailableCats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCat(Cat c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adoptCat(Person p, Cat c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCat(Cat c) {
		// TODO Auto-generated method stub
		
	}

}
