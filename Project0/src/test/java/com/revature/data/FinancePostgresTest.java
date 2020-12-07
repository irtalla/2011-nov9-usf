package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import com.revature.beans.Finance;

public class FinancePostgresTest {
	private FinancePostgres financePostgres = new FinancePostgres();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();
	private PaymentPostgres paymentPostgres = new PaymentPostgres();
	
	@Test
	@Order(1)
	public void testAdd() {
		Finance finance = new Finance();
		finance.setBicycle(bicyclePostgres.getById(8));
		finance.setPaidAmount(50.00);
		finance.setFinancedAmount(700.00);
		finance.setLastPayment(paymentPostgres.getById(1));
		Finance retFinance = financePostgres.add(finance);
		finance.setId(retFinance.getId());
		assertEquals(finance, financePostgres.getById(2));
	}
	
	@Test 
	public void testGetById() {
		Finance finance = new Finance();
		finance.setId(1);
		finance.setBicycle(bicyclePostgres.getById(4));
		finance.setPaidAmount(100.00);
		finance.setFinancedAmount(500.00);
		finance.setLastPayment(paymentPostgres.getById(1));
		assertEquals(finance, financePostgres.getById(1));
	}
	
	@Test
	public void testUpdate() {
		Finance finance = financePostgres.getById(1);
		finance.setPaidAmount(150.00);
		financePostgres.update(finance);
		assertEquals(finance, financePostgres.getById(1));
		finance.setPaidAmount(100.00);
		financePostgres.update(finance);
	}
	
	@Test
	@Order(2)
	public void testDelete() {
		Finance finance = financePostgres.getById(2);
		financePostgres.delete(finance);
		assertEquals(1, financePostgres.getAll().size());
	}
	
	@Test
	public void testGetAll() {
		assertEquals(1, financePostgres.getAll().size());
	}
	
	@Test
	public void testGetByBicycle() {
		Finance finance = new Finance();
		finance.setId(1);
		finance.setBicycle(bicyclePostgres.getById(4));
		finance.setPaidAmount(100.00);
		finance.setFinancedAmount(500.00);
		finance.setLastPayment(paymentPostgres.getById(1));
		assertEquals(finance, financePostgres.getByBicycle(bicyclePostgres.getById(4)));
	}
	
	
}
