package com.revature.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.revature.beans.Person;


public class PersonPostgres_Test {
	
	@DisplayName("Test delete()") 
	@Test
	public void deleteTest() {
		
		PersonPostgres testPersonPostgres = new PersonPostgres();
		
		// Should not delete customers 
		Person personToBeDeleted = new Person(); 
		personToBeDeleted.setId(1);
		personToBeDeleted.getRole().setName("customer");
		boolean didDelete = testPersonPostgres.update(personToBeDeleted);
		Assertions.assertEquals(false, didDelete );
		
		// Should return false if no person was deleted 
		personToBeDeleted.setId(-1);
		personToBeDeleted.getRole().setId(2);
		personToBeDeleted.getRole().setName("employee");
		didDelete = testPersonPostgres.update(personToBeDeleted);
		Assertions.assertEquals(false, didDelete );
		
		// Should return true if person with employee
		// was deleted 
		personToBeDeleted.setId(4);
		personToBeDeleted.getRole().setId(2);
		personToBeDeleted.getRole().setName("employee");
		Assertions.assertEquals(false, didDelete );
	}
	
	@DisplayName("Test getPersonByUserName()")
	@Test
	public void getPersonByUserNameTest() {
		
		
		PersonPostgres testPersonPostgres = new PersonPostgres();
		Person person = null; 
		person = testPersonPostgres.getByUsername("Sam");
		
		Assertions.assertTrue( person != null ); 
		System.out.println( person.getUsername() );
		Assertions.assertTrue( person.getUsername().equalsIgnoreCase("Sam") ); 
		Assertions.assertTrue( person.getRole().getName().
				equalsIgnoreCase("employee") );
		
		
		person = testPersonPostgres.getByUsername("Not in Database 32qwta");
		Assertions.assertTrue( person == null );
	}
	
	@DisplayName("Test add()")
	@Test
	public void addTest() {
		
		PersonPostgres testPersonPostgres = new PersonPostgres();
		Person person = new Person();
		person.setId(-1);
		person.setUsername("Cuzo");
		person.setPassword("808s");
		person.getRole().setId(1);
		
		Person addedPerson = null; 
		addedPerson = testPersonPostgres.add(person); 
		Assertions.assertTrue(
				addedPerson.getId() != -1 &&
				addedPerson.getUsername().equalsIgnoreCase("Cuzo") &&
				addedPerson.getRole().getName().equalsIgnoreCase("customer")
				);
		
	}

	
	
}
