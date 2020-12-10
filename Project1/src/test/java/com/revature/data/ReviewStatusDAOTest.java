package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.revature.models.ReviewStatus;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class ReviewStatusDAOTest {
	private static ReviewStatusDAO rsDao;
	private static ReviewStatus sampleRS;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ReviewStatusDAOFactory rsFactory = new ReviewStatusDAOFactory();
		rsDao = rsFactory.getReviewStatusDao();
		
		sampleRS = new ReviewStatus();
		sampleRS.setId(1);
		sampleRS.setName("Sample RS");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Integer newInt = rsDao.add(sampleRS);
			assertNotEquals(newInt, 0);
			sampleRS.setId(newInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test
	void testGetById() {
		System.out.println("Testing id");
		ReviewStatus rs = rsDao.getById(sampleRS.getId());
		assertEquals(rs, sampleRS);
	}
	
	@Order(3)
	@Test
	void testGetAll() {
		System.out.println("Testing all");
		Set<ReviewStatus> status = rsDao.getAll();
		assertTrue(status.contains(sampleRS));
	}
	
	@Order(4)
	@Test
	void testUpdate() {
		System.out.println("Testing update");
		
		ReviewStatus rs = sampleRS;
		rs.setName("Updated");
		
		try {
			rsDao.update(sampleRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ReviewStatus rs2 = rsDao.getById(rs.getId());
		assertEquals(rs, rs2);
		sampleRS = rs2;
	}
	
	@Order(5)
	@Test
	void testDelete() {
		ReviewStatus rs = sampleRS;
		rsDao.delete(rs);
		
		assertFalse(rsDao.getAll().contains(sampleRS));
	}

}
