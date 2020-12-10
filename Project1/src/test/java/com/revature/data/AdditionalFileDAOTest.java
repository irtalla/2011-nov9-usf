package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.revature.models.AdditionalFile;

@TestMethodOrder(OrderAnnotation.class)
class AdditionalFileDAOTest {
	private static AdditionalFileDAO afDao;
	private static AdditionalFile sampleAF;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		AdditionalFileDAOFactory afFactory = new AdditionalFileDAOFactory();
		afDao = afFactory.getAdditionalFileDao();
		
		sampleAF = new AdditionalFile();
		sampleAF.setId(1);
		sampleAF.setPath("/user_1/pitch_1/file.txt");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Integer newId = afDao.add(sampleAF);
			assertNotEquals(newId, 0);
			sampleAF.setId(newId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test
	void testGetById() {
		AdditionalFile af = afDao.getById(sampleAF.getId());
		assertEquals(af, sampleAF);
	}
	
	@Order(4)
	@Test
	void testGetAll() {
		Set<AdditionalFile> files = afDao.getAll();
		assertTrue(files.contains(sampleAF));
	}
	
	@Order(5)
	@Test
	void testUpdate() {
		AdditionalFile af = sampleAF;
		af.setPath("/user_1/pitch_1/newfile.txt");
		try {
			afDao.update(af);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdditionalFile af2 = afDao.getById(af.getId());
		assertEquals(af, af2);
		sampleAF = af2;
	}
	
	@Order(6)
	@Test
	void testDelete() {
		AdditionalFile af = sampleAF;
		afDao.delete(af);
		
		assertFalse(afDao.getAll().contains(sampleAF));
	}

}
