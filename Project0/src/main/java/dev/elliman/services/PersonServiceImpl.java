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
		if(getAdminUser() == null) {
			//no admin found, a new one should be added
			Person newAdmin = new Person("", "", "admin", "pasword", "Admin");
			personDAO.add(newAdmin);
		}
		//else : an admin user was found, no need to add another
	}

	@Override
	public Person getAdminUser() {
		return getPersonById(0);
	}
	
//	public void addAdminUser() {
//	//check for an admin before creating a new one
//	Person admin = getByID(0);
//	if(admin == null) {
//		//no admin found, add a new one
//		admin = new Person("", "", "admin", "password", "Admin");
//		admin.setID(0);
//		users.add(admin);
//	}
//}

}
