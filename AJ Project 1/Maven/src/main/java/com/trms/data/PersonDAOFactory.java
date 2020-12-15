package com.trms.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
		return new PersonHibernate();
	}

}
