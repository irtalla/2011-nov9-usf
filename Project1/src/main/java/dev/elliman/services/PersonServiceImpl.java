package dev.elliman.services;

import dev.elliman.beans.Person;
import dev.elliman.data.PersonDAO;
import dev.elliman.data.PersonDAOFactory;

public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;
	
	public PersonServiceImpl() {
		personDAO = PersonDAOFactory.getPersonDAO();
	}

	@Override
	public boolean login(String username, String password) {
		Person p = personDAO.getPersonByUsername(username);
		
		if(p == null) {
			return false;
		}
		
		if(password.equals(p.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
