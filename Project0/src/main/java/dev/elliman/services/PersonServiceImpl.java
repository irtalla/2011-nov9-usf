package dev.elliman.services;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.data.PersonDAO;
import dev.elliman.data.PersonDAOFactory;
import dev.elliman.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	public PersonServiceImpl() {
		PersonDAOFactory factory = new PersonDAOFactory();
		personDAO = factory.getPersonDAO();
		
		addAdminUser();
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
		return personDAO.getByUsername(username);
	}

	public void updatePerson(Person p) {
		personDAO.update(p);
	}

	public void deletePerson(Person p) {
		personDAO.delete(p);
	}

	public void addAdminUser() {
		if(getAdminUser() == null) {
			//no admin found, a new one should be added
			Role adminRole = new Role();
			adminRole.setAdmin(personDAO);
			Person newAdmin = new Person("", "", "admin", "password", adminRole);
			personDAO.add(newAdmin);
		}
	}

	@Override
	public Person getAdminUser() {
		return getPersonByUsername("admin");
	}
}
