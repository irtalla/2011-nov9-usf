package com.revature.services;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.data.BikeDAO;
import com.revature.data.BikePostgres;
import com.revature.data.UserDAO;
import com.revature.data.UserPostgres;
import com.revature.exceptions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikeAppServiceTest {

	@DisplayName("Bike fetch & update test")
	@Order(1)
	@Test
	public void BikeServiceTest() {
		BikeDAO bikeDao = new BikePostgres();
		Bike b = bikeDao.getById(1);
		b.setColor("Yellow");
		bikeDao.update(b);
		assertEquals(b, bikeDao.getById(b.getId()));
		System.out.println(bikeDao.getById(b.getId()));
	}
	
	@DisplayName("Test unique username contraint")
	@Order(2)
	@Test
	public void UserServiceTest() {
		UserDAO userDao = new UserPostgres();
		User u = new User();
		u.setUsername("tester");
		u.setPassword("whatever");
		try {
			userDao.add(u);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		}
		assertThrows(NonUniqueUsernameException.class, () -> {
			userDao.add(u);
		});
		userDao.delete(u);
	}
}
