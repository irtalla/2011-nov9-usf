package com.revature.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;
import com.revature.beans.Role;

public class PersonHibernateTest {
	private static PersonHibernate personDao;
	
	@BeforeAll
	public static void setUp() {
		personDao = new PersonHibernate();
	}
	
	@Test
	public void testAddPerson() {
		Person p = new Person();
		p.setUsername("spring");
		p.setPassword("pass");
		Role r = new Role();
		r.setId(1);
		r.setName("Employee");
		p.setRole(r);
		personDao.add(p);
	}
}
