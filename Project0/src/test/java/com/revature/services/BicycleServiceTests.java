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

import com.revature.beans.Bicycle;
import com.revature.beans.Color;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.OfferDAO;
import com.revature.data.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class BicycleServiceTests {
	@Mock
	static BicycleDAO bicycleDAO;
	
	@Mock 
	static PersonDAO personDAO;
	
	@Mock 
	static OfferDAO offerDAO;
	
	@InjectMocks
	static BicycleServiceImpl bicycleServ;
	static Set<Bicycle> mockBicycles = new HashSet<>();
	static Integer mockBicycleSequence = 1;
	
	@Test
	public void addBicycleTest() {
		Bicycle b = new Bicycle();
		Color c = new Color();
		Status s = new Status();
		c.setId(1);
		s.setId(1);
		b.setColor(c);
		b.setStatus(s);
		
		mockBicycles.add(b);
		Bicycle b2 = new Bicycle();
		b2.setId(mockBicycleSequence++);
		b2.setColor(c);
		b2.setStatus(s);
		
		when(bicycleDAO.add(b)).thenReturn(b2);
		assertNotEquals(b.getId().intValue(), bicycleServ.addBicycle(b).intValue());
		verify(bicycleDAO).add(b);
	}

	@Test
	public void getBicycleByIdTest() {
		Bicycle b = new Bicycle();
		b.setId(4);
		mockBicycles.add(b);
		
		when(bicycleDAO.getById(4)).thenReturn(b);
		assertEquals(b, bicycleServ.getBicycleById(4));
		verify(bicycleDAO).getById(4);
	}


	@Test
	public void getBicyclesTest() {
		when(bicycleDAO.getAll()).thenReturn(mockBicycles);
		assertEquals(mockBicycles, bicycleDAO.getAll());
		verify(bicycleDAO).getAll();
	}

	@Test
	public void getAvailableBicyclesTest() {
		Bicycle b = new Bicycle();
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		b.setStatus(s);
		Set<Bicycle> mockAvailableBicycles = new HashSet<>();
		mockAvailableBicycles.add(b);
		mockBicycles.add(b);
		
		when(bicycleDAO.getAvailableBicycles()).then(invocation -> {
			Set<Bicycle> availableBicycles = new HashSet<>();
			for(Bicycle bicycle : mockBicycles) {
				if(bicycle.getStatus().getName().equals("Available")) {
					availableBicycles.add(bicycle);
				}
			}
			return availableBicycles;
		});
		assertEquals(mockAvailableBicycles, bicycleServ.getAvailableBicycles());
		verify(bicycleDAO).getAvailableBicycles();
	}

	@Test
	public void updateBicyclesTest() {
		Bicycle b = new Bicycle();
		bicycleServ.updateBicycle(b);
		verify(bicycleDAO).update(b);
	}


	@Test
	public void deleteBicycleTest() {
		Bicycle b = new Bicycle();
		bicycleServ.deleteBicycle(b);
		verify(bicycleDAO).delete(b);
		
	}
}
