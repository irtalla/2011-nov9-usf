package com.revature.services;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

import beans.Usr;
import beans.Bikes;
import beans.Offers;
import beans.Role;
import data.BikeDAOFactory;
import data.BikeDAO;
import data.BikePostgres;
import data.UserDAO;
import data.UserPostgres;
import exceptions.NonUniqueUsernameException;

public class testServices {
	@DisplayName("Add bike test- Passed") 
	@Test
		public void TestAdd() {
		BikeDAO bikeDao = new BikePostgres();
		Bikes b = new Bikes();
		b.setId(12);
		b.setName("boat");
		bikeDao.add(b);
	}
	@DisplayName("Add user test; fails for throwing exception NBD ")
	@Test
	public void TestUserAdd() throws NonUniqueUsernameException {
		UserDAO userDao = new UserPostgres();
		Usr u = new Usr();
		u.setID(14);
		u.setUsername("Charlie");
		userDao.add(u);
	}
	@DisplayName("Get Role Test- ")
	@Test
	public void TestUserRole() {
		UserDAO userDao = new UserPostgres();
		Usr u = new Usr();
		Role r = new Role();
		r.setId(1);
		r.setName("Customer");
		Role rVerify = u.getRole();
		System.out.println(rVerify.getName());
	}
	@DisplayName("Place offer test-")
	@Test
	public void PlaceOfferTest() {
		UserDAO userDao = new UserPostgres();
		Offers offer = new Offers();
		try {
			userDao.placeOffer(offer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Pending", offer.getStatus());
		
	}
	@DisplayName("Accept offer test-")
	@Test
	public void AcceptOfferTest() {
	UserDAO userDao = new UserPostgres();
	Offers offer = new Offers();
	try {
		userDao.placeOffer(offer);
		userDao.accept(offer.getId());
	}catch (Exception e) {
		e.printStackTrace();
	}
	assertEquals("Accepted", offer.getStatus());
	}

}
