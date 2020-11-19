package dev.RevatureProject.services;

import dev.RevatureProject.Data.PersonDAO;
import dev.RevatureProject.Data.PersonDAOFactory;
import dev.RevatureProject.models.Person;

public class PersonServiceImpl implements PersonService{
	
private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	public Integer addPerson(Person p) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person getPersonByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		
	}



}
