//package com.revature.services;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
////import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
////import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
////import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.TestMethodOrder;
//
//import com.revature.beans.Bike;
//import com.revature.data.BikeCollections;
//import com.revature.data.BikeDAO;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
////@TestMethodOrder(OrderAnnotation.class)
////@TestMethodOrder(Alphanumeric.class)
//public class BikeServiceTest {
//	
//	@BeforeAll
//	public static void beforeAllTests() {
//		System.out.println("Before all tests");
//	}
//	
//	@BeforeEach
//	public void beforeEachTest() {
//		System.out.println("This will happen before each test");
//	}
//	
//	@AfterEach
//	public void afterEachTest() {
//		System.out.println("This will happen after each test");
//	}
//	
//	@AfterAll
//	public static void afterAllTests() {
//		System.out.println("This will happen once after all of the tests");
//	}
//	
////	@Order(3)
//	@DisplayName("Bike Name test")
//	@Test
//	public void testBikeGetterAndSetter() {
//		Bike bk = new Bike();
//		bk.setName("Pokemon");
//		assertEquals("Pokemon", bk.getName());
//	}
//
////	@Order(2)
//	@DisplayName("NullPointerException test")
//	@Test
//	public void testException() {
//		Bike bk = null;
//		
//		assertThrows(NullPointerException.class, () -> {
//			bk.getName();
//		});
//	}
//	
////	@Order(1)
//	@DisplayName("Bike Condition test")
//	@Test 
//	public void testBikeConditionGetterAndSetter() {
//		Bike bk = new Bike();
//		bk.setCondition("New");
//		assertEquals("New", bk.getCondition());
//	}
//	
//	@Test
//	public void testBikeCollectionUpdate() {
//		BikeDAO bikeDao = new BikeCollections();
//		Bike bk = bikeDao.getById(1);
//		System.out.println(bikeDao.getAll());
//		bk.setName("Huffy");
//		bikeDao.update(bk);
//		assertEquals(bk, bikeDao.getById(bk.getId()));
////		System.out.println(bikeDao.getById(bk.getId()));
//	}
//	
//}

package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Brand;
import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.data.BikeDAO;
import com.revature.data.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {
	@Mock
	static BikeDAO bikeDao;
	
	@Mock
	static PersonDAO personDao;
	
	@InjectMocks
	static BikeServiceImpl bikeServ;
	
	static Set<Bike> bikesMock = new HashSet<>();
	static Integer bikeSequenceMock = 1;
	
	@Test
	public void testAddValidBike() {
		Bike bk = new Bike();
		Brand b = new Brand();
		b.setId(1);
		Status s = new Status();
		s.setId(1);
		bk.setStatus(s);
		
		bikesMock.add(bk);
		Bike bk2 = new Bike();
		bk2.setId(bikeSequenceMock++);
		bk2.setBrand(bk.getBrand());
		bk2.setStatus(bk.getStatus());
		
		// when...thenReturn methods allow us to
		// set up the mock implementation of the mock DAO
		when(bikeDao.add(bk)).thenReturn(bk2);
		assertNotEquals(bk.getId().intValue(), bikeServ.addBike(bk).intValue());
		
		// verify method makes sure that mock
		// implementation was used (or that it was
		// used as many times as expected)
		verify(bikeDao).add(bk);
		// verify(bikeDao, times(1)).add(c)
		// verifyNoMoreInteractions(bikeDao);
	}
	
	@Test
	public void testGetBikes() {
		when(bikeDao.getAll()).thenReturn(bikesMock);
		assertEquals(bikesMock, bikeServ.getBikes());
		verify(bikeDao).getAll();
	}
	
	@Test
	public void testGetAvailableBikes() {
		Bike bk = new Bike();
		Status s = new Status();
		s.setId(2);
		s.setName("Available");
		Set<Bike> availableBikesMock = new HashSet<>();
		availableBikesMock.add(bk);
		bikesMock.add(bk);
		
		when(bikeDao.getAvailableBikes()).then(invocation -> {
			Set<Bike> available = new HashSet<>();
			for (Bike bike : bikesMock) {
				if (bike.getStatus().getName().equals("Available")) {
					available.add(bike);
				}
			}
			return available;
		});
		assertEquals(availableBikesMock, bikeServ.getAvailableBikes());
		verify(bikeDao).getAvailableBikes();
	}
	
	@Test
	public void testGetBikeById() {
		Bike bk = new Bike();
		bk.setId(10);
		bikesMock.add(bk);
		
		when(bikeDao.getById(10)).thenReturn(bk);
		assertEquals(bk, bikeServ.getBikeById(10));
		verify(bikeDao).getById(10);
	}
	
	@Test
	public void testUpdateBike() {
		Bike bk = new Bike();
		bikeServ.updateBike(bk);
		verify(bikeDao).update(bk);
	}
	
	@Test
	public void testRemoveBike() {
		Bike bk = new Bike();
		bikeServ.removeBike(bk);
		verify(bikeDao).delete(bk);
	}
	
	@Test
	public void testPurchaseBikeRoom1() {
		Person p = new Person();
		Bike bk = new Bike();
		p.setId(1);
		p.setPassword("123");
		p.setUsername("joe");
		Role r = new Role();
		r.setId(1);
		r.setName("alive");
		p.setRole(r);
		bk.setId(1);
		Brand b = new Brand();
		b.setId(1);
		Status s = new Status();
		s.setName("Available");
		s.setId(1);
		bk.setBrand(b);
		bk.setStatus(s);
		
		bikeServ.purchaseBike(p, bk);
		
		assertEquals(bk.getStatus().getName(),"Purchased");
		assertTrue(p.getBikes().contains(bk));
		verify(bikeDao).update(bk);
		verify(personDao).update(p);
	}
}


