package dev.elliman.services;

import dev.elliman.beans.Person;
import dev.elliman.data.PersonDAO;
import dev.elliman.data.PersonDAOFactory;
import dev.elliman.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	public PersonServiceImpl() {
		PersonDAOFactory factory = new PersonDAOFactory();
		personDAO = factory.getPersonDAO();
	}

	public Integer createUser(Person person) throws NonUniqueUsernameException{
		String username = person.getUsername();

		if(getPersonByUsername(username) == null) {
			return personDAO.add(person);
		} else {
			throw new NonUniqueUsernameException();
		}
	}

	public Person getPersonById(Integer id) {
		return personDAO.getByID(id);
	}

	public Person getPersonByUsername(String username) {
		for(Person person : personDAO.getAll()) {
			//usernames dont care about case
			if(person.getUsername().equalsIgnoreCase(username)) {
				return person;
			}
		}
		return null;
	}

	public void updatePerson(Person p) {
		personDAO.update(p);
	}

	public void deletePerson(Person p) {
		personDAO.delete(p);
	}

	public void addAdminUser() {
		personDAO.addAdminUser();
	}

}
