package com.trms.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trms.beans.Request;

class RequestServiceImplTest {

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	void test() {
		RequestService rs = new RequestServiceImpl(); 
		List<Request> rList = rs.getByPersonId(6);
		System.out.println(rList.toString());
		for (Request r : rList) {
			System.out.println(r.getId());
		}
		assertTrue(rList.size() > 0);
	}
	
	@Test
	void getAllTest() {
		RequestService rs = new RequestServiceImpl(); 
		List<Request> rList = rs.getByPersonId(6);
		System.out.println(rList.toString());
		for (Request r : rList) {
			System.out.println(r.getId());
		}
		assertTrue(rList.size() > 0);
	}

}
