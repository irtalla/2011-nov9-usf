package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.data.BicycleDAO;
import com.revature.data.OfferDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {
	@Mock
	static BicycleDAO bicycleDAO;
	
	@Mock 
	static PersonDAO personDAO;
	
	@Mock 
	static OfferDAO offerDAO;
	
	@InjectMocks
	static BicycleServiceImpl bicycleServ;
	static Set<Person> mockPersons = new HashSet<>();
	static Integer mockPersonSequence = 1;
	
	@Test
	public void addPersonTest() throws NonUniqueUsernameException {
		
	}

	@Test
	public void getPersonByIdTest() {
		
	}

	@Test
	public void getPersonByUsernameTest() {
		
	}

	@Test
	public void getPersonByBicycleTest() {
		
	}

	@Test
	public void updatePersonTest() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void deletePersonTest() {
		// TODO Auto-generated method stub
		
	}
}
