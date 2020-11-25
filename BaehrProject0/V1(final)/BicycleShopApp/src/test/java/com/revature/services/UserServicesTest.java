package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.User;
import com.revature.data.BicycleDAO;
import com.revature.data.BicycleDAOFactory;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchaseDAOFactory;

class UserServicesTest {
	
	private static UserServicesImpl userv;
	
	@BeforeAll
	public static void setUp() {
		userv = new UserServicesImpl();
	}

	@Test
	@Order(1)
	void getUserByIdTest() {
		User u = new User();
		u = userv.getUserById(1);
		assertTrue(u.getUserID() == 1);
	}
	
	@Test
	@Order(2)
	void getUserByUsernameTest() {
		User u = new User();
		u = userv.getUserByUsername("user1");
		assertTrue(u.getUsername().equals("user1"));
	}
	
	@Test
	@Order(3)
	void updateTest() {
		User u = userv.getUserById(9);
		User u2 = new User();
		u2.setAccountBalance(-99.0);
		u2.setPassword("password");
		u2.setRoleID(1);
		u2.setUserID(9);
		userv.updateUser(u2);
		//System.out.println(u);
		assertTrue(u.getAccountBalance() == -99.0);
		assertTrue(u.getPassword().equals("password"));
		assertTrue(u.getRoleID().equals(1));
		// following changes for idempotence
		u.setAccountBalance(0.0);
		u.setPassword("pass8");
		u.setRoleID(2);
		u.setUserID(9);
		u.setUsername("user8");
	}
	
	@Test
	@Order(4)
	void addAndDeleteTest() {
		User u = new User();
		u.setAccountBalance(0.0);
		u.setPassword("testpass");
		u.setRoleID(2);
		u.setUsername("testname");
		userv.addUser(u);
		Integer id = u.getUserID();
		assertTrue(id != null); //tests add method
		
		userv.deleteUser(u);
		User u2 = userv.getUserById(id);
		assertTrue(u2 == null); //tests delete method
		
	}
	
}
