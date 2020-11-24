package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Payment;

public class PaymentPostgresTests {
	
	@Test
	public void testAdd()
	{
		Payment a = new Payment();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setCustomerID(tCust.getID());
		
		PaymentDAO aDao = DAOFactory.getPaymentDAO();
		Payment b = aDao.add(a);
		Payment c = new Payment();
		assertNotEquals(c,b);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	@Test
	public void testGetByID()
	{
		
		Payment a = new Payment();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setCustomerID(tCust.getID());
		
		PaymentDAO aDao = DAOFactory.getPaymentDAO();
		Payment b = aDao.add(a);
		Payment c = aDao.getById(b.getID());
		assertEquals(b,c);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	
	@Test
	public void testGetAll()
	{
		
		Payment a = new Payment();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setCustomerID(tCust.getID());
		
		PaymentDAO aDao = DAOFactory.getPaymentDAO();
		Payment b = aDao.add(a);
		
		Set<Payment> results = aDao.getAll();
		
		assertTrue(results.contains(b));
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
		
	}
	
	@Test
	public void testUpdate()
	{
		Payment a = new Payment();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setCustomerID(tCust.getID());
		
		PaymentDAO aDao = DAOFactory.getPaymentDAO();
		Payment b = aDao.add(a);
		
		b.setAmount(5f);;
		aDao.update(b);
		
		Payment result = aDao.getById(b.getID());
		assertEquals(result.getAmount(),5f);
		aDao.delete(b);
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
	
	
	@Test
	public void testDelete()
	{
		Payment a = new Payment();
		
		Customer tCust = new Customer();
		Bicycle tBicycle = new Bicycle();
		tCust = DAOFactory.getCustomerDAO().add(tCust);
		tBicycle = DAOFactory.getBikeDAO().add(tBicycle);
		
		a.setCustomerID(tCust.getID());
		
		PaymentDAO aDao = DAOFactory.getPaymentDAO();
		Payment b = aDao.add(a);
		aDao.delete(b);
		
		Set<Payment> results = aDao.getAll();
		
		assertFalse(results.contains(b));
		DAOFactory.getBikeDAO().delete(tBicycle);
		DAOFactory.getCustomerDAO().delete(tCust);
	}
	
}
