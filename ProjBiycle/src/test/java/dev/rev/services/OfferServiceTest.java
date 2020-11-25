package dev.rev.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.data.BicycleDAO;
import dev.rev.data.OfferDAO;
import dev.rev.data.PersonDAO;
import dev.rev.model.Bicycle;
import dev.rev.model.Person;
import dev.rev.model.Role;


//@TestMethodOrder(Alphanumeric.class)
//@TestMethodOrder(OrderAnnotation.class)
public class OfferServiceTest {
	
	// this method will run ONCE before any of your tests are run
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("This will happen before any of the tests");
	}
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("This will happen before each test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("This will happen after each test");
	}
	
	@AfterAll
	public static void afterAllTests() {
		System.out.println("This will happen once after all of the tests");
	}
	
	// Assertion methods
	// assertEquals
	// assertTrue
	// assertFalse
	// assertArrayEquals
	// assertThrows
	
	@Test
	public void testofferaccepted() {
		OfferServices os=new OfferServiceIm();
		

	}
	@Test
	public void makepayent() {
		
		BicycleService b=new BicycleServiceImp();
		b.updatepayment(3, 10);
		//assertEquals(b,)
		

	}
	@Test 
	public void addbicycle() {
		
	Bicycle bike= new Bicycle();
	bike.setBicycle_status("New");
	bike.setBrand("Revature");
	bike.setColor("Orange");
	bike.setPrice(500);
	bike.setQuantity(4);
	BicycleService bs= new BicycleServiceImp();
	
	try {
		bs.addBicycle(bike);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//assertEquals(bike,bs.addBicycle(bike));
	
	
	}
	
	
	@Test 
	public void getbicyclebybrand() {
		
		Bicycle bike = new Bicycle();
		String brand= "RoadRunner";
		
		BicycleService bs = new BicycleServiceImp();
		bs.getBicyclebyBrand(brand);
		
		assertEquals(bike,bs.getBicyclebyBrand(brand));
	}

	@Test
	public void addperson() {
		
		Person per=new Person();
		per.setId(50);
		per.setName("Revature");
		per.setPassword("1");
		Role role= new Role();
		role.setId(1);
		role.setRole_name("Customer");
		per.setRole(role);
		per.setUsername("Rev1");
		
		PersonDAO pd = null;
		try {
			pd.add(per);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(per,pd.getByUsername(per.getUsername(), per.getPassword()));
		
		
	}
	
	
}