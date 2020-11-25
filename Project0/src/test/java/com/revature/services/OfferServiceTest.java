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
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.OfferDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;


@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
	
	@Mock
	static OfferDAO oDao;
	
	@Mock
	static PersonDAO pDao;
	
	@Mock
	static BicycleDAO bDao;
	
	@InjectMocks
	static OfferServiceImpl oServ;
	
	static Set<Offer> oMocks = new HashSet<>();
	static Integer sequenceMock =1;
	
	@Test
	public void testAddOffer() throws NullPointerException, NonUniqueUsernameException {
		Offer o = new Offer();
		o.setId(1);
		oMocks.add(o);
		Offer o2 = new Offer();
		o2.setId(sequenceMock +=1);
		
		when(oDao.add(o)).thenReturn(o2);
		assertNotEquals(o.getId().intValue(), oServ.addOffer(o2).intValue());
		verify(oDao).add(o);
	}
	@Test
	public void testGetOfferById () {
		Offer o = new Offer();
		o.setId(24);
		oMocks.add(o);
		
		when(oDao.getById(24)).thenReturn(o);
		assertEquals(o, oServ.getOfferById(24));
		verify(oDao).getById(24);
		oMocks.remove(o);
	}
	
	@Test
	public void testGetAllOffers() {
		when(oDao.getAll()).thenReturn(oMocks);
		assertEquals(oMocks, oServ.getAllOffers());
		verify(oDao).getAll();
	}
	
	@Test
	public void testUpdateOffer() {
		Offer o = new Offer();
		oServ.updateOffer(o);
		verify(oDao).update(o);
	}
	
	@Test
	public void testRemoveOffer() {
		Offer o = new Offer();
		oServ.deleteOffer(o);
		verify(oDao).delete(o);
	}
	
	@Test
	public void testAcceptOffer() {
		Offer o = new Offer();
		o.setId(100);
		oServ.acceptOffer(o.getId());
		
		verify(oDao).accept(100);
		
	}
}
