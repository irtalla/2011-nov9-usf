package com.revature.services;

import com.revature.beans.Request;
import java.util.Set;
import com.revature.beans.Person;
import com.revature.data.*;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDAO;
	//private RequestDAO requestDAO;
	//private PitchDAO pitchDAO;
	//private DraftDAO draftDAO;

	public PersonServiceImpl() {
		PersonDAOFactory personDAOFactory = new PersonDAOFactory();
		personDAO = personDAOFactory.getPersonDAO();
	}

	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDAO.add(p).getId();
	}

	public Person getPersonById(Integer id) {
		return personDAO.getById(id);
	}

	public Person getPersonByUsername(String username) {
		return personDAO.getByUsername(username);
	}

	public void updatePerson(Person p) {
		personDAO.update(p);
	}

	public void deletePerson(Person p) {
		personDAO.delete(p);
	}

	public Set<Request> getRequests(Person p){
		return personDAO.getRequests(p);
	}

}
