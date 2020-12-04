package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
<<<<<<< HEAD
	public class PersonDAOFactory(Person p, Integer id, String username, String password); {
		getPersonDao(p) {
			return collectionDao;
		}
	}
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDao();
=======
	
	
	
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
>>>>>>> 258ec0b5e441ebf080c18be8d9464450e316e717
	}
}

	@Override
<<<<<<< HEAD
	public Integer addPerson(Person p) {
=======
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
>>>>>>> 258ec0b5e441ebf080c18be8d9464450e316e717
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		
		System.out.println("");
		
		return null;
=======
		return personDao.getByUsername(username);
>>>>>>> 14c4f16f445f8bc716af654a0929ded9dd89d004
	}

	@Override
	public void updatePerson(Person p) {
		personDao.update(p);
	}

	@Override
	public void deletePerson(Person p) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		
		int i=p.getId();
		p.setId(0);
		p.setPassword("");
		p.setRole(null);
		p.setUsername("");
		
		personDao.delete(p);
		
		
		
		
=======
		personDao.delete(p);
>>>>>>> 14c4f16f445f8bc716af654a0929ded9dd89d004
	}

}
