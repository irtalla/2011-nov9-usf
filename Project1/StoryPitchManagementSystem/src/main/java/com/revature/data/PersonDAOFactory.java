package com.revature.data;

//import com.revature.data.PersonDAO;
//import com.revature.data.PersonPostgreSQL;

public class PersonDAOFactory {
	
	public PersonDAO getPersonDAO() {        
		return new PersonPostgreSQL();
	}

}
