package com.revature.data;

import com.revature.beans.Person;

public class PersonDAOFactory implements GenericDAOFactory<Person>{
	@Override
	public PersonDAO getDAO() {
		return new PersonHibernate();
	}
}
