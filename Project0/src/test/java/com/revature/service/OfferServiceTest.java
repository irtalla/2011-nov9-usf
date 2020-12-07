package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.*;
import com.revature.beans.Bicycle;
import com.revature.beans.Category;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.OfferService;
import com.revature.services.PersonService;

public class OfferServiceTest {
	OfferService offerService = new OfferService();
	
	@Test
	public void testSelectedOffer() {
		// Honestly, i have no idea how to test this ... 
	}
}
