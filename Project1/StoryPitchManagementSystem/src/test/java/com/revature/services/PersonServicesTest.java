package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;

class PersonServicesTest {
	
	private static PersonServicesImpl pServ;
	
	@BeforeAll
	public static void setUp() {
		//pServ.System.out.println("Got here");
		pServ = new PersonServicesImpl();
	}

	@Test
	void getByPersonIDTest() {
		Person p = pServ.getByPersonID(28); //user 28 has story pitches, info requests, change requests and approvals
		//System.out.println(p);
		assertTrue(p.getStoryPitches().size() > 0);
		assertTrue(p.getApprovals().size() > 0);
		assertTrue(p.getInfoRequests().size() > 0);
		assertTrue(p.getChangeRequests().size() > 0);
		Person p2 = pServ.getByPersonID(16); // user 16 has a rejection
		//System.out.println(p2);
		assertTrue(p2.getRejections().size() > 0);
		Person p3 = pServ.getByPersonID(1); // user 1 has info responses
		//System.out.println(p3);
		assertTrue(p3.getInfoResponses().size() > 0);
	}

}
