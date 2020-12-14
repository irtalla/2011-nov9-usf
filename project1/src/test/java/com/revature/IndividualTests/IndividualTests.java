package com.revature.IndividualTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.revature.beans.Individual;
import com.revature.controllers.IndividualController;
import com.revature.services.IndividualService;
import com.revature.services.IndividualServiceImpl;

public class IndividualTests {

	IndividualService individualServ = new IndividualServiceImpl();
	IndividualController ic = new IndividualController();	
	/*
	 * 
	 * public Individual getIndividualByUsername(String username) {
*/
	@Test
    public void test_login() {
		
		Individual kyle = individualServ.getIndividualByUsername("kyle");
		assertEquals(kyle.getPasswd(), "pass");
		
    }
}
