package com.revature.services;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.data.BicycleDAO;
import com.revature.data.BicycleDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService{
	//private BicycleDAO bicycleDAO;
	private PersonDAO personDAO;
	//private OfferDAO offerDAO;
	
	public PersonServiceImpl() {
		//BicycleDAOFactory bicycleDAOFactory = new BicycleDAOFactory();
		//bicycleDAO = bicycleDAOFactory.getBicycleDAO();
		
		PersonDAOFactory personDAOFactory = new PersonDAOFactory();
		personDAO = personDAOFactory.getPersonDAO();
		
		//OfferDAOFactory offerDAOFactory = new OfferDAOFactory();
		//offerDAO = offerDAOFactory.getOfferDAO();
	}
	
	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDAO.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDAO.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDAO.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		personDAO.update(p);
	}

	@Override
	public void deletePerson(Person p) {
		personDAO.delete(p);
		
	}
	
}
