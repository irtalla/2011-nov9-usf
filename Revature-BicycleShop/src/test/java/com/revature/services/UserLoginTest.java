package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.revature.services.UserFunctions;


public class UserLoginTest {

	@Test
	public void testUserValidationIfThere() {
		UserFunctions userCollections = new UserFunctions();
		
		assertTrue(userCollections.validatePotentialUser("banebadibi", "parameciumfatality"));
		
	}
	
}
