package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.User;

class UserPostgresTest {
	private UserDAOFactory uFactory = new UserDAOFactory();
	private UserDAO uDao = uFactory.getUserDAO();
	
	@Test
	void testAdd() {
		User a = new User();
		a.setUsername("uses1232");
		a.setPassword("password");
		User b = new User();
		b.setUsername(a.getUsername());
		b.setPassword(a.getPassword());
		User c = null;
		try {
			c = uDao.add(a);
			assertNotEquals(b.getId(), c.getId());
			uDao.delete(c);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetById() {
		User a = new User();
		a.setUsername("usde5");
		a.setPassword("password");
		User b = null;
		try {
			b = uDao.add(a);
			User c = uDao.getById(b.getId());
			assertEquals(b, c);
			uDao.delete(b);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	void testGetAll() {
		Set<User> set = uDao.getAll();
		int initSize = set.size();
		User a = new User();
		a.setUsername("a2b");
		a.setPassword("password");
		User b = null;
		try {
			b = uDao.add(a);
			set = uDao.getAll();
			assertTrue(set.contains(a));
			assertNotEquals(initSize, set.size());
			uDao.delete(b);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testUpdate() {
		User a = new User();
		a.setUsername("a6seb");
		a.setPassword("dafeae");
		User b = null;
		try {
			b = uDao.add(a);
			User c = new User();
			c.setId(b.getId());
			c.setUsername(b.getUsername());
			c.setPassword("asdfaef");
			uDao.update(c);
			assertNotEquals(b, uDao.getById(b.getId()));
			uDao.delete(c);
			
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test 
	void testDelete() {
		User a = new User();
		a.setUsername("asdfasdf");
		a.setPassword("asdfsadfsad");
		User b = null;
		try {
			b = uDao.add(a);
			assertTrue(uDao.getAll().contains(b));
			uDao.delete(b);
			assertFalse(uDao.getAll().contains(b));
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
	}

}
