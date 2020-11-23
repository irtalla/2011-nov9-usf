package dev.rev.services;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.data.PersonDAO;
import dev.rev.data.PersonDAOFactory;
import dev.rev.model.Person;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
	}

	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return personDao.add(p).getId();
	}

	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}

	public Person getPersonByUsername(String username,String password) {
		// TODO Auto-generated method stub
		return personDao.getByUsername(username,password);
	}

	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		personDao.update(p);
	}

	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		personDao.delete(p);
	}

	@Override
	public String checkusername(String username) {
		
		// TODO Auto-generated method stub
		return personDao.checkusername(username);
	}

	
}
