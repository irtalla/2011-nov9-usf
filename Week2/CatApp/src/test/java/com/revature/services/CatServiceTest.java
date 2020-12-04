package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.revature.beans.Role;
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
		
	public void testGetCats() {
		when(catDao.getAll()).thenReturn(catsMock);
		assertEquals(catsMock, catServ.getCats());
		verify(catDao).getAll();
	}
	
	@Test
	public void testGetAvailableCats() {
		Cat c = new Cat();
		Status s = new Status();
		s.setId(2);
		s.setName("Available");
		Set<Cat> availableCatsMock = new HashSet<>();
		availableCatsMock.add(c);
		catsMock.add(c);
		
		when(catDao.getAvailableCats()).then(invocation -> {
			Set<Cat> available = new HashSet<>();
			for (Cat cat : catsMock) {
				if (cat.getStatus().getName().equals("Available")) {
					available.add(cat);
				}
			}
			return available;
		});
		assertEquals(availableCatsMock, catServ.getAvailableCats());
		verify(catDao).getAvailableCats();
	}
	
	@Test
	public void testGetCatById() {
		Cat c = new Cat();
		c.setId(10);
		catsMock.add(c);
		
		when(catDao.getById(10)).thenReturn(c);
		assertEquals(c, catServ.getCatById(10));
		verify(catDao).getById(10);
	}
	
	@Test
	public void testUpdateCat() {
		Cat c = new Cat();
		catServ.updateCat(c);
		verify(catDao).update(c);
	}
	
	@Test
	public void testRemoveCat() {
		Cat c = new Cat();
		catServ.removeCat(c);
		verify(catDao).delete(c);
	}
	
	@Test
	public void testAdoptCatRoom1() {
		Person p = new Person();
		Cat c = new Cat();
		p.setId(1);
		p.setPassword("123");
		p.setUsername("joe");
		Role r = new Role();
		r.setId(1);
		r.setName("alive");
		p.setRole(r);
		c.setId(1);
		Breed b = new Breed();
		b.setId(1);
		Status s = new Status();
		s.setName("Available");
		s.setId(1);
		c.setBreed(b);
		c.setStatus(s);
		
		catServ.adoptCat(p, c);
		
		assertEquals(c.getStatus().getName(),"Adopted");
		assertTrue(p.getCats().contains(c));
		verify(catDao).update(c);
		verify(personDao).update(p);
	}
}
