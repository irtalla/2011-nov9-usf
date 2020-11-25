package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import com.revature.beans.Bicycle;
import com.revature.beans.User;
import com.revature.beans.Purchase;

import org.junit.jupiter.api.Test;

class PurchaseDAOTester {
	
	private static PurchaseDAO purchaseDao;

	private static BicycleDAO bicycleDao;
	
	@BeforeAll
	public static void setUp() {
		PurchaseDAOFactory purchaseDaoFactory = new PurchaseDAOFactory();
		purchaseDao = purchaseDaoFactory.getPurchaseDAO();
		
		BicycleDAOFactory bicycleDaoFactory = new BicycleDAOFactory();
		bicycleDao = bicycleDaoFactory.getBicycleDAO();
	}

	@Test
	@Order(1)
	void getByIdTest() {
		Purchase p = null;
		p = purchaseDao.getById(1);
		//System.out.println(p);
		assertTrue(p != null);
	}
	
	@Test
	@Order(2)
	void getAllTest() {
		Set<Purchase> purchases = null;
		purchases = purchaseDao.getAll();
		//System.out.println(purchases.size());
		assertTrue(purchases != null);
	}
	
	@Test
	@Order(3)
	void getByUserIdTest() {
		Set<Purchase> purchases = new HashSet<Purchase>();
		purchases = purchaseDao.getByUserId(6);
		//System.out.println(purchases);
		assertTrue(purchases.size() > 0);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		Purchase p = purchaseDao.getById(1);
		Purchase p2 = new Purchase();
		p2.setBicycle(p.getBicycle());
		p2.setPrice((p.getPrice() + 50));
		p2.setPurchase_id(p.getPurchase_id());
		p2.setUser_id(p.getUser_id());
		purchaseDao.update(p2);
		Purchase p3 = purchaseDao.getById(1);;
		assertTrue(p3.getPrice() == p2.getPrice());
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		Purchase p =  new Purchase();
		p.setBicycle(bicycleDao.getById(1));
		p.setPrice(p.getBicycle().getPrice());
		p.setUser_id(6);
		Purchase p2 = purchaseDao.add(p);
		Integer id = (p2.getPurchase_id());
		assertTrue(id != null && id != 0);
		
		Purchase p3 = purchaseDao.getById(id);
		//System.out.println(p3);
		purchaseDao.delete(p3);
		//System.out.println(purchaseDao.getById(id));
		assertTrue(purchaseDao.getById(id) == null);
	}
	
	


}
