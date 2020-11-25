package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.BicycleType;

class BicycleTypeDAOTester {
	private static BicycleTypeDAO bicycleTypeDao;
	
	@BeforeAll
	public static void setUp() {
		BicycleTypeDAOFactory bicycleTypeDaoFactory = new BicycleTypeDAOFactory();
		bicycleTypeDao = bicycleTypeDaoFactory.getBicycleTypeDAO();
	}

	@Test
	@Order(1)
	void getAllTest() {
		Set<BicycleType> bikeTypes = new HashSet<BicycleType>();
		bikeTypes = bicycleTypeDao.getAll();
		assertTrue(bikeTypes.size() > 0);
	}
	
	@Test
	@Order(2)
	void getById() {
		BicycleType bt = new BicycleType();
		bt = bicycleTypeDao.getById(1);
		System.out.println(bt);
		assertTrue(bt.getId() == 1);
	}

}
