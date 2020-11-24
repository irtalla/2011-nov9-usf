//package com.revature.services;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.revature.beans.Bike;
//import com.revature.beans.Person;
//import com.revature.beans.Privilege;
//import com.revature.beans.Shop;
//import com.revature.data.BikeDAO;
//import com.revature.data.BikePostgres;
//import com.revature.data.PersonDAO;
//import com.revature.data.PersonPostgres;
//import com.revature.data.PrivilegePostgres;
//import com.revature.data.PrivilegeDAO;
//import com.revature.data.ShopDAO;
//import com.revature.data.ShopPostgres;
//import com.revature.exceptions.NonUniqueUsernameException;
//import com.revature.exceptions.UnauthorizedBusinessActionException;
//
//public class PersonShopBikeServicesTest {
//	private PersonDAO personDao;
//	private ShopDAO shopDao;
//	private BikeDAO bikeDao;
//	private PrivilegeDAO privilegeDao;
//	
//	private Person owner;
//	private Shop shop;
//	private Person manager;
//	private Person employee;
//	private Set<Person> people;
//	private Privilege addingBikes;
//	private Bike bike;
//	
//	@BeforeAll
//	public void initializeDaos() {
//		personDao = new PersonPostgres();
//		shopDao = new ShopPostgres();
//		bikeDao = new BikePostgres();
//		privilegeDao = new PrivilegePostgres();
//	}
//	
//	@BeforeEach
//	public void initializeData() throws Exception {
//		owner = new Person();
//		owner.setUsername("Owner");
//		owner = personDao.add(owner);
//		
//		shop = new Shop();
//		shop.setName("shop");
//		shop = shopDao.add(shop);
//		
//		manager = new Person();
//		manager.setUsername("Manager");
//		manager = personDao.add(manager);
//		
//		manager = personDao.hireManager(owner, shop, manager);
//		
//		employee = new Person();
//		employee.setUsername("Employee");
//		employee = personDao.add(owner);
//		
//		employee = personDao.hireEmployee(manager, shop, employee);
//		
//		addingBikes = new Privilege();
//		addingBikes.setName("Adding bikes to inventory");
//		
//		bike = bikeDao.add(new Bike());
//	}
//	
//	@Test
//	public void testAddBike() {
//		//expect all people (even new-hires) to be able to add bikes to the inventory of the shop's they work at by default
//		Set<Person> people = new HashSet<>(Arrays.asList(owner, manager, employee));
//		people.forEach(person -> {
//			personDao.addBikeToShop(person, shop, bike);
//			assertTrue(shop.getInventory().contains(bike));
//		});
//		
//		personDao.revokePrivilege(owner, shop, addingBikes, employee);
//		assertThrows(UnauthorizedBusinessActionException.class, () -> personDao.addBikeToShop(employee, shop, bike));
//	}
//}
