package com.bikeshop.dao;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
		return new PersonPostgres();
	}
}
