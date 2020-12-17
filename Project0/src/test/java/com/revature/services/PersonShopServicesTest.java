//package com.revature.services;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.revature.beans.Privilege;
//import com.revature.beans.Person;
//import com.revature.beans.Shop;
//import com.revature.data.PersonDAO;
//import com.revature.data.PersonPostgres;
//import com.revature.data.PrivilegeDAO;
//import com.revature.data.PrivilegePostgres;
//import com.revature.data.ShopDAO;
//import com.revature.data.ShopPostgres;
//import com.revature.exceptions.NonUniqueUsernameException;
//import com.revature.exceptions.UnauthorizedBusinessActionException;
//
//public class PersonShopServicesTest {
//	private PersonDAO personDao;
//	private ShopDAO shopDao;
//	private Person owner;
//	
//	@BeforeAll
//	public void initializeDaos() {
//		personDao = new PersonPostgres();
//		shopDao = new ShopPostgres();
//	}
//	
//	@BeforeEach
//	public void initializeOwner() {
//		owner = new Person();
//		owner.setUsername("Owner");
//	}
//	
//	@Test
//	public void testAddPerson() throws NonUniqueUsernameException {
//		Person addedPerson = addPerson(owner);
//		assertTrue(personDao.getAll().contains(addedPerson));
//	}
//	
//	@Test
//	public void testAddPersonWithNonUniqueUsername() throws NonUniqueUsernameException {
//		addPerson(owner);
//		Person p2 = new Person();
//		p2.setUsername("person 1");
//		assertThrows(NonUniqueUsernameException.class, () -> personDao.add(p2));
//	}
//	
//	@Test
//	public void testAddShop() throws Exception {
//		Shop shop = new Shop();
//		shop.setName("shop");
//		shop.setOwner(owner);
//		Shop updatedShop = shopDao.add(shop);
//		
//		//assert that adding a shop updates the owner as well
//		Person updatedOwner = personDao.getById(updatedShop.getOwner().getId());
//		assertNotNull(updatedOwner);
//		assertTrue(updatedOwner.getOwnedShops().contains(updatedShop));
//	}
//	
//	@Test
//	public void testHireSubordinates() throws Exception {
//		personDao.add(owner);
//		
//		Shop shop = new Shop();
//		shop.setName("shop");
//		shop.setOwner(owner);
//		Shop updatedShop = shopDao.add(shop);
//		
//		Person manager = new Person();
//		manager.setUsername("Manager");
//		personDao.add(manager);
//		
//		Person updatedManager = personDao.hireManager(owner, shop, manager);
//		
//		//assert that shop now contains manager among its managers
//		assertNotNull(updatedManager);
//		updatedShop = shopDao.getById(shop.getId());
//		
//		assertTrue(updatedShop.getManagers().contains(updatedManager));
//		assertEquals(updatedManager.getShopWorksAt(), updatedShop);
//		
//		//assert that manager has the default authority to hire and fire subordinates:
//		assertTrue(personDao.mayHireSubordinates(updatedManager, updatedShop));
//		
////		Privilege firingSubordinate = new Privilege();
////		hiringSubordinate.setName("firing subordinates");
////		Privilege updatedFiringAction = privilegeDao.add(firingSubordinate);
////		
//		assertTrue(personDao.mayFireSubordinates(updatedManager, updatedShop));
//		
//		//assert that new hires can add bikes to the inventory
////		Privilege addingBike = new Privilege();
////		addingBike.setName("Adding bikes to inventory");
////		Privilege updatedAddingBike = privilegeDao.add(addingBike);
//		
//		Person employee0 = new Person();
//		employee0.setUsername("Employee 0");
//		personDao.add(employee0);
//		
//		Person updatedEmployee0 = personDao.hireEmployee(updatedManager, updatedShop, employee0);
//		assertEquals(updatedEmployee0.getShopWorksAt(), updatedShop);
//		assertTrue(personDao.mayAddBikesToInventory(updatedEmployee0, updatedShop));
//		
//		//assert that owner has the capability to hire direct subordinates for a manager
//		Person employee1 = new Person();
//		owner.setUsername("Employee 1");
//		
//		Person updatedEmployee1 = addPerson(employee1);
//		
//		updatedEmployee1 = personDao.hireEmployee(owner, updatedShop, updatedEmployee1);
//		assertEquals(updatedManager, updatedEmployee1.getManager());
//		assertTrue(updatedManager.getDirectSubordinates().contains(updatedEmployee1));
//		
//		//assert that manager can hire and fire an employee by default:
//		updatedEmployee1 = personDao.fireEmployee(updatedManager, shop, updatedEmployee1);
//		assertFalse(updatedEmployee1.getManager().equals(updatedManager));
//		assertFalse(updatedManager.getDirectSubordinates().contains(updatedEmployee1));
//		
//		updatedEmployee1 = personDao.hireEmployee(updatedManager, shop, updatedEmployee1);
//		assertTrue(updatedEmployee1.getManager().equals(updatedManager));
//		assertTrue(updatedManager.getDirectSubordinates().contains(updatedEmployee1));
//		
//		//assert that manager can invest authority in employee to hire and fire subordinates
//		PrivilegeDAO privilegeDao = new PrivilegePostgres();
//		Privilege hiringSubordinate = new Privilege();
//		hiringSubordinate.setName("hiring subordinates");
//		Privilege updatedHiringAction = privilegeDao.add(hiringSubordinate);
//		
//		updatedEmployee1 = personDao.grantPrivilege(updatedManager, shop, updatedHiringAction, updatedEmployee1);
//		assertTrue(personDao.mayHireSubordinates(updatedManager, updatedShop));
//		
//		//assert that owner can revoke and grant an employee's authority to hire and fire subordinates
//		updatedEmployee1 = personDao.revokePrivilege(owner, updatedShop, updatedHiringAction, updatedEmployee1);
//		assertFalse(personDao.mayHireSubordinates(updatedManager, updatedShop));
//		
//		//assert that attempting to hire someone without the privilege to hire subordinates will raise an exception:
//		Person employee2 = new Person();
//		employee2.setUsername("Employee 2");
//		Person updatedEmployee2 = addPerson(employee2);
//		updatedEmployee2 = personDao.hireEmployee(updatedManager, updatedShop, updatedEmployee2);
//		
//		final Person emp1 = updatedEmployee1;
//		final Shop shop1 = updatedShop;
//		final Person emp2 = updatedEmployee2;
//		
//		assertThrows(UnauthorizedBusinessActionException.class, () -> personDao.hireEmployee(emp1, shop1, emp2));
//	}
//	
//	public Person addPerson(Person p) throws NonUniqueUsernameException {
//		return personDao.add(p);
//	}
//}
