package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;

class DAOFactoryTester {
	
	private static BicycleDAO bicycleDao;
	private static BicycleTypeDAO bicycleTypeDao;
	private static UserDAO userDao;
	private static PurchaseDAO purchaseDao;
	private static OfferDAO offerDao;
	
	@BeforeAll
	public static void setUp() {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		bicycleDao = bicycleDaoFactory.getBicycleDAO();

		BicycleTypeDAOFactory bicycleTypeDaoFactory = new BicycleTypeDAOFactory();
		bicycleTypeDao = bicycleTypeDaoFactory.getBicycleTypeDAO();

		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();

		PurchaseDAOFactory purchaseDaoFactory = new PurchaseDAOFactory();
		purchaseDao = purchaseDaoFactory.getPurchaseDAO();

		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
	}

	@Test
	void bicycleDaoTest() {
		assertTrue(bicycleDao instanceof BicycleDAO);
		assertFalse(bicycleDao == null);
	}

	@Test
	void bicycleTypeDaoTest() {
		assertTrue(bicycleTypeDao instanceof BicycleTypeDAO);
		assertFalse(bicycleTypeDao == null);
	}
	
	@Test
	void userDaoTest() {
		assertTrue(userDao instanceof UserDAO);
		assertFalse(userDao == null);
	}
	
	@Test
	void purchaseDaoTest() {
		assertTrue(purchaseDao instanceof PurchaseDAO);
		assertFalse(purchaseDao == null);
	}
	
	@Test
	void offerDaoTest() {
		assertTrue(offerDao instanceof OfferDAO);
		assertFalse(offerDao == null);
	}
	
	

}
