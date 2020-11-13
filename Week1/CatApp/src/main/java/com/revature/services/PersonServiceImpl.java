package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
	public class PersonDAOFactory(Person p, Integer id, String username, String password); {
		getPersonDao(p) {
			return collectionDao;
		}
	}
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDao();
	}
}

	@Override
	public Integer addPerson(Person p) {
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

}
