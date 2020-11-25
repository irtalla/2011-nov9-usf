package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.data.BicycleDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	static BicycleDAO bDao;
	
	@Mock
	static PersonDAO pDao;
	
	@InjectMocks
	static PersonServiceImpl pServ;

	static Set<Bicycle> bMocks = new HashSet<>();
	static Set<Person> pMocks = new HashSet<>();
	static Integer sequenceMock = 1;
	
	
	@Test
	public void testAddValidPerson() throws NullPointerException, NonUniqueUsernameException{
		Person p = new Person();
		p.setId(1);
		pMocks.add(p);
		Person p2 = new Person();
		p2.setId(sequenceMock+=1);
		
		when(pDao.add(p)).thenReturn(p2);
		assertNotEquals(p.getId().intValue(), pServ.addPerson(p).intValue());
		verify(pDao).add(p);
	}
	
	@Test
	public void testGetPersonById () {
		Person p = new Person();
		p.setId(24);
		pMocks.add(p);
		
		when(pDao.getById(24)).thenReturn(p);
		assertEquals(p, pServ.getPersonById(24));
		verify(pDao).getById(24);
		pMocks.remove(p);
	}
	
	@Test
	public void testGetPersonByUsername() {
		Person p = new Person();
		p.setUsername("test");
		pMocks.add(p);
		
		when(pDao.getByUsername("test")).thenReturn(p);
		assertEquals(p, pServ.getPersonByUsername("test"));
		verify(pDao).getByUsername("test");
		pMocks.remove(p);
	}
	
	
	@Test
	public void testUpdatePerson() {
		Person p = new Person();
		pServ.updatePerson(p);
		verify(pDao).update(p);
	}
	
	@Test
	public void testRemovePerson() {
		Person p = new Person();
		pServ.deletePerson(p);
		verify(pDao).delete(p);
	}
	
	@Test
	public void testGetAll() {
		when(pDao.getAll()).thenReturn(pMocks);
		assertEquals(pMocks, pServ.getAll());
		verify(pDao).getAll();
	}




}
