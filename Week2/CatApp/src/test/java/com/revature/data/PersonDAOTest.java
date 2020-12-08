package com.revature.data;

import org.junit.jupiter.api.BeforeAll;

public class PersonDAOTest {
	private static PersonDAO personDao;
	
	@BeforeAll
	public static void setUp() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
		
//		personDao = new PersonPostgres();
	}
}
