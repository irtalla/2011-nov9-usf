package com.revature.services;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions.*;

import beans.Usr;
import beans.Bikes;
import beans.Offers;
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
	@DisplayName("Add user test- ")
	@Test
	public void TestUserAdd() throws NonUniqueUsernameException {
		UserDAO userDao = new UserPostgres();
		Usr u = new Usr();
		u.setUsrId(8);
		userDao.add(u);
		
	}

}
