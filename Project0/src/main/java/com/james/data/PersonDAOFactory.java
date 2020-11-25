package com.james.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
        
        return new PersonPostgres();
    }
}
