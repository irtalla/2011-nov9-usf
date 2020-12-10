package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.models.PitchStage;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class PitchStageDAOTest {
	private static PitchStageDAO psDao;
	private static PitchStage samplePS;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PitchStageDAOFactory psFactory = new PitchStageDAOFactory();
		psDao = psFactory.getPitchStageDao();
		
		samplePS = new PitchStage();
		samplePS.setId(1);
		samplePS.setName("Sample");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Integer newInt = psDao.add(samplePS);
			assertNotEquals(newInt, 0);
			samplePS.setId(newInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Order(2)
	@Test
	void testGetById() {
		PitchStage ps = psDao.getById(samplePS.getId());
		assertEquals(ps, samplePS);
	}
	
	@Order(3)
	@Test
	void testGetAll() {
		Set<PitchStage> stages = psDao.getAll();
		assertTrue(stages.contains(samplePS));
	}
	
	@Order(4)
	@Test
	void testUpdate() {
		PitchStage ps = samplePS;
		ps.setName("Updated Sample");
		try {
			psDao.update(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PitchStage ps2 = psDao.getById(ps.getId());
		assertEquals(ps, ps2);
		samplePS = ps2;
	}
	
	@Order(6)
	@Test
	void testDelete() {
		PitchStage ps = samplePS;
		psDao.delete(ps);
		
		assertFalse(psDao.getAll().contains(samplePS));
	}
}
