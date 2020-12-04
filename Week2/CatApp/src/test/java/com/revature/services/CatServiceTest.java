package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.CatDAO;
import com.revature.data.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {
	@Mock
	static CatDAO catDao;
	
	@Mock
	static PersonDAO personDao;
	
	@InjectMocks
	static CatServiceImpl catServ;
	
	static Set<Cat> catsMock = new HashSet<>();
	static Integer catSequenceMock = 1;
	
	@Test
	public void testAddValidCat() {
		Cat c = new Cat();
		Breed b = new Breed();
		b.setId(1);
		Status s = new Status();
		s.setId(1);
		c.setBreed(b);
		c.setStatus(s);
		
		catsMock.add(c);
		Cat c2 = new Cat();
		c2.setId(catSequenceMock++);
		c2.setBreed(c.getBreed());
		c2.setStatus(c.getStatus());
		
		// when...thenReturn methods allow us to
		// set up the mock implementation of the mock DAO
		when(catDao.add(c)).thenReturn(c2);
		assertNotEquals(c.getId().intValue(), catServ.addCat(c).intValue());
		
		// verify method makes sure that mock
		// implementation was used (or that it was
		// used as many times as expected)
		verify(catDao).add(c);
		// verify(catDao, times(1)).add(c)
		// verifyNoMoreInteractions(catDao);
	}
	
	@Test
	public void testAdoptCat() {
		Cat c = new Cat();
		Person p = new Person();
		
		catServ.adoptCat(p, c);
		
		assertTrue(p.getCats().contains(c));
		
		verify(personDao).update(p);
	}
}
