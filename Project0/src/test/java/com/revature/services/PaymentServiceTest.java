package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.revature.beans.Payment;
import com.revature.beans.Person;
import com.revature.data.PaymentDAO;

import com.revature.exceptions.NonUniqueUsernameException;


@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

	@Mock
	static PaymentDAO payDao;
	
	@InjectMocks
	static PaymentServiceImpl payServ;
	
	static Set<Payment> payMocks = new HashSet<>();
	static Integer sequenceMock = 1;
	
	@Test
	public void testAddPayment() throws NullPointerException, NonUniqueUsernameException {
		Payment pay = new Payment();
		pay.setId(1);
		payMocks.add(pay);
		Payment pay2 = new Payment();
		pay2.setId(sequenceMock += 1);
		
		when(payDao.add(pay)).thenReturn(pay2);
		assertNotEquals(pay.getId().intValue(), payServ.addPayment(pay2).intValue());
		verify(payDao).add(pay);
	}
	
	@Test
	public void testGetPaymentById () {
		Payment pay = new Payment();
		pay.setId(24);
		payMocks.add(pay);
		
		when(payDao.getById(24)).thenReturn(pay);
		assertEquals(pay, payServ.getPaymentById(24));
		verify(payDao).getById(24);
		payMocks.remove(pay);
	}
	
	@Test
	public void testUpdatePayment() {
		Payment pay = new Payment();
		payServ.updatePayment(pay);
		verify(payDao).update(pay);
	}
	
	@Test
	public void testRemovePayment() {
		Payment pay = new Payment();
		payServ.deletePayment(pay);
		verify(payDao).delete(pay);
	}
	
	@Test
	public void testGetAll() {
		when(payDao.getAll()).thenReturn(payMocks);
		assertEquals(payMocks, payServ.getAllPayments());
		verify(payDao).getAll();
	}
	
	
	@Test
	public void testGetPaymentsByUserName() {
		Person p = new Person();
		p.setId(1);
//		Payment pay1 = new Payment();
//		Payment pay2 = new Payment();
//		payMocks.add(pay1);
//		payMocks.add(pay2);
		payDao.getPaymentsByUserId(p.getId());
		verify(payDao).getPaymentsByUserId(1);
	}

}
