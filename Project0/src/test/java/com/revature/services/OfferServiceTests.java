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
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.data.BicycleDAO;
import com.revature.data.OfferDAO;
import com.revature.data.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTests {
	@Mock
	static BicycleDAO bicycleDAO;
	
	@Mock 
	static PersonDAO personDAO;
	
	@Mock 
	static OfferDAO offerDAO;
	
	@InjectMocks
	static BicycleServiceImpl bicycleServ;
	static Set<Offer> mockOffers = new HashSet<>();
	static Integer mockOfferSequence = 1;
	
	@Test
	public void addOfferTest() {
		
	}

	@Test
	public void getOfferByIdTest() {
		
	}

	@Test
	public void getOffersTest() {
		
	}

	@Test
	public void getOffersByBicycleTest() {
		
	}

	@Test
	public void getOffersByPersonTest() {
		
	}

	@Test
	public void updateOfferTest() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void acceptOfferTest(Offer o) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void rejectOfferTest(Offer o) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void deleteOfferTest(Offer o) {
		// TODO Auto-generated method stub
		
	}
}
