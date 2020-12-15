package com.trms.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trms.beans.Request;

class RequestHibernateTest {
	private RequestDAO requestDao;

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
	void testGetById() {
		RequestDAOFactory rdf = new RequestDAOFactory();
		requestDao = rdf.getRequestDAO();
		List<Request> rList = requestDao.getByRequestorId(6);
		for (Request r : rList) {
			System.out.println(r.getId());
		}
		assertTrue(rList.size() > 0);
	}
//
//	@Test
//	void testGetAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAdd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetByRequestorId() {
//		fail("Not yet implemented");
//	}
//
}
