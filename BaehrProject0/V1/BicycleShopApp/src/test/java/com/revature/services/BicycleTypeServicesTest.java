package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.revature.beans.BicycleType;

class BicycleTypeServicesTest {
	private static BicycleTypeServicesImpl btserv;
	
	@BeforeAll
	public static void setUp() {
		btserv = new BicycleTypeServicesImpl();
	}
	
	@Test
	@Order(1)
	void getAllTest() {
		Set<BicycleType> btset = new HashSet<BicycleType>();
		btset = btserv.getAll();
		assertTrue(btset.size() > 0);
	}
	
	@Test
	@Order(2)
	void getByIdTest() {
		BicycleType bt = new BicycleType();
		bt = btserv.getById(1);
		assertTrue(bt.getId() == 1);
	}

}
