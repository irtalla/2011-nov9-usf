package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import com.revature.beans.Bicycle;
import com.revature.beans.User;

@TestMethodOrder(Alphanumeric.class)

class UserDAOTester {
	
	private static UserDAO userDao;
	
	@BeforeAll
	public static void setUp() {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();
	}

	@Test
	@Order(1)
	void testGetById() {
		User u = null;
		u = userDao.getById(1);
		//System.out.println(u);
		assertTrue(u != null);
	}
	
	@Test
	@Order(2)
	void testGetByUsername() {
		User u = null;
		u = userDao.getByUsername("user1");
		//System.out.println(u);
		assertTrue(u != null);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<User> users = null;
		users = userDao.getAll();
		//System.out.println(users);
		assertTrue(users != null);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		User user = userDao.getById(5);
		User user2 = new User();
		user2.setUserID(user.getUserID());
		user2.setRoleID(user.getRoleID());
		user2.setUsername(user.getUsername());
		user2.setPassword(user.getPassword());
		user2.setAccountBalance((user.getAccountBalance() + 50.0));
		userDao.update(user2);
		User user3 = userDao.getById(5);
		//System.out.println(user);
		//System.out.println(user2);
		//System.out.println(user3);
		assertTrue(user2.getAccountBalance().equals(user3.getAccountBalance()));
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		User u =  new User();
		u.setAccountBalance(0.0);
		u.setUsername("test");
		u.setPassword("test");
		u.setRoleID(2);
		User u2 = userDao.add(u);
		Integer id = (u2.getUserID());
		assertTrue(id != 0);
		
		User u3 = userDao.getById(id);
		System.out.println(u3);
		System.out.println(id);
		userDao.delete(u3);
		System.out.println(userDao.getById(id));
		assertTrue(userDao.getById(id) == null);
	}

}
