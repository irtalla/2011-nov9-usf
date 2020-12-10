package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.models.StoryType;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
@TestMethodOrder(OrderAnnotation.class)
class StoryTypeDAOTest {
	private static StoryTypeDAO stDao;
	private static StoryType sampleST;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		StoryTypeDAOFactory stFactory = new StoryTypeDAOFactory();
		stDao = stFactory.getStoryTypeDao();
		
		sampleST = new StoryType();
		sampleST.setId(1);
		sampleST.setName("SampleStoryType");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Integer newInt = stDao.add(sampleST);
			assertNotEquals(newInt, 0);
			sampleST.setId(newInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test
	void testGetById() {
		StoryType st = stDao.getById(sampleST.getId());
		assertEquals(st, sampleST);
	}
	
	@Order(3)
	@Test
	void testGetAll() {
		Set<StoryType> types = stDao.getAll();
		assertTrue(types.contains(sampleST));
	}

	@Order(4)
	@Test
	void testUpdate() {
		StoryType st = sampleST;
		st.setName("Updated Sample");
		try {
			stDao.update(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
		StoryType st2 = stDao.getById(st.getId());
		assertEquals(st, st2);
		sampleST = st2;
	}
	
	@Order(5)
	@Test
	void testDelete() {
		StoryType st = sampleST;
		stDao.delete(st);
		
		assertFalse(stDao.getAll().contains(sampleST));
	}
}
