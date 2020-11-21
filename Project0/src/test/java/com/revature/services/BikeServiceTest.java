package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Model;
import com.revature.beans.Bike;
import com.revature.beans.Status;
import com.revature.data.BikeDAO;
import com.revature.data.UserDAO;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {
	@Mock
	static BikeDAO bikeDao;
	
	@Mock
	static UserDAO userDao;
	
	@InjectMocks
	static BikeServiceImpl BikeServ;
	
	static Set<Bike> BikesMock = new HashSet<>();
	static Integer BikeSequenceMock = 1;
	
	@Test
	public void testAddValidBike() {
		Bike bike = new Bike();
		Model model = new Model();
		model.setId(1);
		Status status = new Status();
		status.setId(1);
		bike.setModel(model);
		bike.setStatus(status);
		
		BikesMock.add(bike);
		Bike bike2 = new Bike();
		bike2.setId(BikeSequenceMock++);
		bike2.setModel(bike.getModel());
		bike2.setStatus(bike.getStatus());
		
		// when...thenReturn methods allow us to
		// set up the mock implementation of the mock DAO
		when(bikeDao.add(bike)).thenReturn(bike2);
		assertNotEquals(bike.getId().intValue(), BikeServ.addBike(bike).intValue());
		
		// verify method makes sure that mock
		// implementation was used (or that it was
		// used as many times as expected)
		verify(bikeDao).add(bike);
		// verify(BikeDao, times(1)).add(c)
		// verifyNoMoreInteractions(BikeDao);
	}
}
