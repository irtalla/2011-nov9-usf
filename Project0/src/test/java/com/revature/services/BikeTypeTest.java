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
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.BikeTypeDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;


@ExtendWith(MockitoExtension.class)
public class BikeTypeTest {
	
	@Mock
	static BicycleDAO bDao;
	
	@Mock
	static BikeTypeDAO btDao;
	
	@InjectMocks
	static BikeTypeServiceImpl btServ;
	
	static Set<Bicycle> bMocks = new HashSet<>();
	static Set<BikeType> btMocks = new HashSet<>();
	static Integer sequenceMock = 1;
	
	
	@Test
	public void testAddBikeType() throws NullPointerException, NonUniqueUsernameException{
		BikeType bt = new BikeType();
		bt.setId(1);
		btMocks.add(bt);
		BikeType bt2 = new BikeType();
		bt2.setId(sequenceMock+=1);
		
		when(btDao.add(bt)).thenReturn(bt2);
		assertNotEquals(bt.getId().intValue(), btServ.addBikeType(bt2).intValue());
		verify(btDao).add(bt);
	}

	
	@Test
	public void testGetBikeTypeById() {
		BikeType bt = new BikeType();
		bt.setId(24);
		btMocks.add(bt);
		when(btDao.getById(24)).thenReturn(bt);
		assertEquals(bt, btServ.getBikeTypeById(24));
		verify(btDao).getById(24);
		btMocks.remove(bt);
	}
	
	
	@Test
	public void testGetBikeTypeByName() {
		BikeType bt = new BikeType();
		bt.setTypeName("TestBike");
		btMocks.add(bt);
		when(btDao.getBikeTypeByName("TestBike")).thenReturn(bt);
		assertEquals(bt, btServ.getBikeTypeByName("TestBike"));
		verify(btDao).getBikeTypeByName("TestBike");
		btMocks.remove(bt);
	}
	
	
	@Test
	public void testGetBikeTypes() {
		when(btDao.getAll()).thenReturn(btMocks);
		assertEquals(btMocks, btServ.getBikeTypes());
		verify(btDao).getAll();
	}
	
	@Test
	public void testUpdateBikeType() {
		BikeType bt = new BikeType();
		btServ.updateBikeType(bt);
		verify(btDao).update(bt);
	}
	
	@Test
	public void testDeleteBikeType() {
		BikeType bt = new BikeType();
		btServ.deleteBikeType(bt);
		verify(btDao).delete(bt);
	}
}
