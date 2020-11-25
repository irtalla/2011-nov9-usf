import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;


public class BikeTests {

	private static PersonService personServ = new PersonServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	
	
	@Test
	public void testGetBikeById() {
		Bike bike = bikeServ.getBikeById(1); 
		assertEquals("Huffy",bike.getModel());
	}
	
	@Test
	public void testGetPersonById() {
		Person person = personServ.getPersonById(21);
		assertEquals("JohnWayne",person.getUsername());
	}
	
	@Test
	public void testGetOfferById() {
		Offer offer = offerServ.getOfferById(9); 
		assertEquals(450, offer.getPrice());
	}
		

	
	@Test
	public void testGetPersonByUsername() {
		Person user = personServ.getPersonByUsername("User");
		String password = user.getPassword();
		assertEquals("pass", password);
		user = personServ.getPersonByUsername("Employee");
		password = user.getPassword();
		assertEquals("pass", password);
	}

	@Test
	public void testGetAvailableOffers() {
		Set<Offer> offers = offerServ.getAvailableOffers();
		assertEquals(1,offers.size()); // there are 1  pending offers in the database
	}

	
	
    @Test
    public void testAddPerson() throws NonUniqueUsernameException {
    	Person person = new Person();
    	person.setUsername("Person");
    	person.setPassword("password");
    	Role r = new Role();
    	r.setId(1);
    	r.setName("employee");
    	person.setRole(r);
    	personServ.addPerson(person);
    	Person addedPerson = personServ.getPersonByUsername("Person");
    	assertEquals("Person", addedPerson.getUsername());
    	personServ.deletePerson(addedPerson); // deleting person after having added him
    }
	
	
	@Test
	public void testGetAllPersons() {
		Set<Person> persons = personServ.getPersons();
		assertEquals(10,persons.size()); // there are 10 persons in the database
	}
	

    @Test
    public void testAddBike()  {
		Bike bike = new Bike();
		bike.setModel("TestBike");
		bike.setColor("Red");
		bike.setPrice(199.99f);
		Status s = new Status();
		Role r = new Role();
    	r.setId(1);
    	r.setName("Available");
    	bike.setStatus(s);
		bikeServ.addBike(bike);
		Bike addedBike = bikeServ.getBikeById(40);
    	assertEquals("TestBike", addedBike.getModel());
    	bikeServ.removeBike(addedBike); // deleting bike after adding it for test
    	
    }
	@Test
	public void testRetrieveBikesFromPerson() {
		Person person = personServ.getPersonById(5);
		Set<Bike> bikes = new HashSet<>();
		bikes= person.getBikes();

		assertEquals(5, bikes.size()); // person 5 has 5 bikes
		person = personServ.getPersonById(3);
		bikes= person.getBikes();
		assertEquals(1,bikes.size());   // person 3 has 1 bike
	}
}
