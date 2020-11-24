package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Offer;

public class OfferPostgresTest {

	

	@Test
	public void testAdd()
	{
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		Offer c = new Offer();
		assertNotEquals(c,b);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	@Test
	public void testGetByID()
	{
		
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		Offer c = aDao.getById(b.getID());
		assertEquals(b,c);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	@Test
	public void testGetAll()
	{
		
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		
		Set<Offer> results = aDao.getAll();
		
		assertTrue(results.contains(b));
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
		
	}
	
	@Test
	public void testUpdate()
	{
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		
		b.setPrice(5f);
		aDao.update(b);
		
		Offer result = aDao.getById(b.getID());
		assertEquals(result.getPrice(),5f);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	
	@Test
	public void testDelete()
	{
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		aDao.delete(b);
		
		Set<Offer> results = aDao.getAll();
		
		assertFalse(results.contains(b));
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	@Test
	public void testAcceptOffer()
	{
		Offer a = new Offer();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setOwner(tCust.getID());
		a.setItem(tBicycle.getID());
		a.setPrice(15f);
		
		Offer g = new Offer();
		g.setOwner(tCust.getID());
		g.setItem(tBicycle.getID());
		g.setPrice(25f);
		
		OfferDAO aDao = DAOFactory.getOfferDAO();
		Offer b = aDao.add(a);
		g = aDao.add(g);
		
		aDao.AcceptOffer(g);
		
		Offer result1 = aDao.getById(b.getID());
		Offer result2 = aDao.getById(g.getID());
		assertEquals(result1.getStatus().getID(),2);
		assertEquals(result2.getStatus().getID(),1);
		aDao.delete(b);
		aDao.delete(g);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
}
