package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Bicycle;
import com.revature.beans.BikeType;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;


@ExtendWith(MockitoExtension.class)
public class BicycleServiceTest {
	@Mock
	static BicycleDAO bikeDao;
	
	@Mock
	static PersonDAO pDao;
	
	@InjectMocks
	static BikeServiceImpl bServ;
	
	static Set<Bicycle> bikesMock = new HashSet<>();
	static Integer sequenceMock = 1;
	
	@Test
	public void testAddValidBicycle() throws NullPointerException, NonUniqueUsernameException {
		Bicycle b = new Bicycle();
		BikeType bt = new BikeType();
		bt.setId(1);
		Status s = new Status();
		s.setId(1);
		b.setBt(bt);
		b.setStatus(s);
		
		bikesMock.add(b);
		Bicycle b2 = new Bicycle();
		b2.setId(sequenceMock +=1);
		b2.setBt(b.getBt());
		b2.setStatus(b.getStatus());
		
		when(bikeDao.add(b)).thenReturn(b2);
		assertNotEquals(b.getId().intValue(), bServ.addBicycle(b).intValue());
		
		verify(bikeDao).add(b);
	}

	
	@Test
	public void testGetBikeById() {
		Bicycle b = new Bicycle();
		b.setId(24);
		bikesMock.add(b);
		
		when(bikeDao.getById(24)).thenReturn(b);
		assertEquals(b, bServ.getBicycleById(24));
		verify(bikeDao).getById(24);
		bikesMock.remove(b);
	}

	@Test
	public void testGetAllBikes() {
		when(bikeDao.getAll()).thenReturn(bikesMock);
		assertEquals(bikesMock, bServ.getBicycles());
		verify(bikeDao).getAll();
	}
	
	@Test
	public void testGetAvailableBikes() {
		Bicycle b = new Bicycle();
		Status s = new Status();
		s.setId(2);
		s.setName("available");
		Set<Bicycle> availaBikes = new HashSet<>();
		availaBikes.add(b);
		bikesMock.add(b);
		
		when(bikeDao.getAvailableBicycles()).then(invocation -> {
			Set<Bicycle> available = new HashSet<>();
			for(Bicycle bike : bikesMock) {
				if(bike.getStatus().getName().equals("available")) {
					available.add(bike);
				}
			}
			return available;
		});
		assertEquals(availaBikes, bServ.getAvailableBicycles());
		verify(bikeDao).getAvailableBicycles();
	}
	
	@Test
	public void testUpdateBike() {
		Bicycle b = new Bicycle();
		bServ.updateBicycle(b);
		verify(bikeDao).update(b);
	}
	
	@Test
	public void testRemoveBike() {
		Bicycle b = new Bicycle();
		bServ.deleteBicycle(b);
		verify(bikeDao).delete(b);
	}

	

}
