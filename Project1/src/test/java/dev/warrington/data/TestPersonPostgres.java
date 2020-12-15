package dev.warrington.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Person;
import dev.warrington.beans.Role;
import dev.warrington.exceptions.NonUniqueUsernameException;

public class TestPersonPostgres {
	
	@Test
	public void addAndGetByUsername() throws NonUniqueUsernameException {
		PersonPostgres test = new PersonPostgres();
		Person p1 = null;
		Person p2 = new Person();
		Role r = new Role();
		r.setId(1);
		r.setName("author");
		p2.setFirstName("test");
		p2.setLastName("test");
		p2.setPassHash("test");
		p2.setUsername("test");
		p2.setSalt("test");
		p2.setRole(r);
		
		p1 = test.add(p2);
		assertNotNull(p2);
		
		test.delete(p2.getId());
	}
	
}