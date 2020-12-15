package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.PersonPostgreSQL;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class PersonDAOTest {
	
	//@Mock
	private static PersonDAO personDao;
	private static PersonPostgreSQL SQLDao;
	
	@BeforeAll
	public static void setUp() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
		PersonPostgreSQL SQLDao = Mockito.mock(PersonPostgreSQL.class);
	}

	@Test
	@Order(1)
	void testGetById() {
		Person u = null;
		u = personDao.getByPersonID(1);
		//u = SQLDao.getByPersonId(1);
		//System.out.println(u);
		assertTrue(u != null);
	}
	
	@Test
	@Order(2)
	void testGetByUsername() {
		Person u = null;
		u = personDao.getByUsername("user1");
		//System.out.println(u);
		assertTrue(u != null);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<Person> persons = null;
		persons = personDao.getAll();
		//System.out.println(persons);
		assertTrue(persons.size() > 1);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		Person person = personDao.getByPersonID(26); //John Brown - my test user
		Person person2 = new Person();
		person2.setPersonID(person.getPersonID());
		person2.setRoleID(person.getRoleID());
		person2.setName(person.getName());
		person2.setPassword(person.getPassword());
		person2.setPoints((person.getPoints() - 10));
		person2.setUsername(person.getUsername());
		personDao.update(person2);
		Person person3 = personDao.getByPersonID(26);
		//System.out.println(person);
		//System.out.println(person2);
		//System.out.println(person3);
		assertTrue(person2.getPoints().equals(person3.getPoints()));
		// undoing update
		personDao.update(person);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		Person u =  new Person();
		u.setPoints(100);
		u.setName("test");
		u.setPassword("test");
		u.setRoleID(2);
		u.setUsername("test");
		Person u2 = personDao.add(u);
		Integer id = (u2.getPersonID());
		assertTrue(id != 0);
		
		Person u3 = personDao.getByPersonID(id);
		//System.out.println(u3);
		//System.out.println(id);
		personDao.delete(u3);
		//System.out.println(personDao.getByPersonId(id));
		assertTrue(personDao.getByPersonID(id) == null);
	}

}