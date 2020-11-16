package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.revature.services.UserFunctions;


public class UserLoginTest {

	
	//note: this one is actually not voluntary
	//it will be changed to reflect a thing.
	@Test
	public void testUserValidationIfThere() {
		UserFunctions userCollections = new UserFunctions();
		
		assertTrue(userCollections.validatePotentialUser("banebadibi", "parameciumfatality"));
	}
	
	@Test
	public void testUserValidationIfNotThere() {
		UserFunctions userCollections = new UserFunctions();
		
		assertFalse(userCollections.validatePotentialUser("lesenfantesclotildes", "14605"));
	}
	
}
