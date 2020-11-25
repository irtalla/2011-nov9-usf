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

//@TestMethodOrder(Alphanumeric.class)
class BicycleDAOTester {
	
	private static BicycleDAO bicycleDao;
	private Integer id;
	
	@BeforeAll
	public static void setUp() {
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		bicycleDao = bicycleDaoFactory.getBicycleDAO();
	}
	
	@Test
	@Order(1)
	void addTest() {
		Bicycle b =  bicycleDao.getById(1);
		Bicycle b2 = bicycleDao.add(b);
		System.out.println(b2);
		id = b2.getId();
		//System.out.println(id);
		assertTrue(b == b2);
	}
	
	@Test
	@Order(2)
	void deleteTest() {
		System.out.println(id);
		Bicycle b =  bicycleDao.getById(id);
		bicycleDao.delete(b);
		assertTrue(bicycleDao.getById(id) == null);
	}
	
	@Test
	@Order(3)
	void getAllTest() {
		Set<Bicycle> b =  bicycleDao.getAll();
		//System.out.println(b.size());
		assertFalse(b == null);
	}
	
	@Test
	@Order(4)
	void getAvailablelTest() {
		Set<Bicycle> b =  bicycleDao.getAvailable();
		//System.out.println(b.size());
		assertFalse(b == null);
	}

	@Test
	@Order(5)
	void getBicycleByIdTest() {
		Bicycle b =  bicycleDao.getById(1);
		//System.out.println(b);
		assertFalse(b == null);
	}
	
	@Test
	@Order(6)
	void getBicycleByUserIdTest() {
		Set<Bicycle> b =  bicycleDao.getByUserId(1);
		//System.out.println(b);
		assertFalse(b == null);
	}
	
	
	@Test
	@Order(7)
	void updateBicycleTest() {
		Bicycle b =  bicycleDao.getById(1);
		Bicycle n = new Bicycle();
		n.setBicycleType(b.getBicycleType());
		n.setId(b.getId());
		n.setOwner_id(b.getOwner_id());
		n.setPrice(b.getPrice());
		n.setStatus(b.getStatus());
		n.setYear((b.getYear() - 2));
		bicycleDao.update(n);
		Bicycle b2 =  bicycleDao.getById(1); 
		//System.out.println(b);
		//System.out.println(n);
		//System.out.println(b2);
		//System.out.println(n.getYear().equals( b2.getYear()));
		assertTrue(n.getYear().equals( b2.getYear()));
	}
	

}
