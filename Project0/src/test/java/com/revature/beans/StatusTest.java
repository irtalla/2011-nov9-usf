package com.revature.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class StatusTest {
	
	@Test
	public void testChangeToAvaliable() {
		Status testStatus = new Status();
		testStatus.setId(2);
		testStatus.setStatus("Unavailable");
		testStatus.changeToAvaliable();
		assertEquals(1, testStatus.getId());
		assertEquals("Available", testStatus.getStatus());
	}
	
	@Test
	public void testChangeToUnavaliable() {
		Status testStatus = new Status();
		testStatus.changeToUnavaliable();
		assertEquals(2, testStatus.getId());
		assertEquals("Unavailable", testStatus.getStatus());
	}
}
