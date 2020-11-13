package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	
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
<<<<<<< HEAD
		//PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		//personDao = personDaoFactory.getPersonDao();
=======
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
>>>>>>> da704a2191c99435b3748685f8b2263ec6a423af
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
		personDao.update(p);
		
		
		
	}

	@Override
	public void deletePerson(Person p) {
		int i=p.getId();
		p.setId(0);
		p.setPassword("");
		p.setRole(null);
		p.setUsername("");
		
		personDao.delete(p);
	}

}
