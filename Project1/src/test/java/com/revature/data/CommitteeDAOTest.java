package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.exceptions.NonUniqueCommitteeException;
import com.revature.models.Committee;
import com.revature.models.Genre;
import com.revature.models.User;

@TestMethodOrder(OrderAnnotation.class)
class CommitteeDAOTest {
	private static CommitteeDAO committeeDao;
	private static Committee sampleCommittee;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CommitteeDAOFactory cFactory = new CommitteeDAOFactory();
		committeeDao = cFactory.getCommitteeDao();
		
		sampleCommittee = new Committee();
		sampleCommittee.setId(1);
		Genre g = new Genre();
		g.setId(1);
		g.setName("Romance");
		sampleCommittee.setGenre(g);
		sampleCommittee.setName(g.getName() + " Committee");
		
	}

//	@Order(1)
//	@Test
//	void testAdd() {
//		try {
//			Integer newId = committeeDao.add(sampleCommittee);
//			assertNotEquals(newId, 0);
//			sampleCommittee.setId(newId);
//			
//		} catch (NonUniqueCommitteeException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Order(2)
//	@Test
//	void testGetById() {
//		System.out.println("Testing id");
//		Committee a = committeeDao.getById(sampleCommittee.getId());
//		assertEquals(a, sampleCommittee);
//	}
//	
//	@Order(3)
//	@Test
//	void testGetByGenre() {
//		System.out.println("Testing genre");
//		Committee a = committeeDao.getByGenre(sampleCommittee.getGenre());
//		assertEquals(a, sampleCommittee);
//	}
//	
//	@Order(4)
//	@Test
//	void testGetAll() {
//		System.out.println("Testing all");
//		Set<Committee> committees = committeeDao.getAll();
//		System.out.println(committees);
//		assertTrue(committees.contains(sampleCommittee));
//	}
//
//	@Order(5)
//	@Test
//	void testUpdate() throws NonUniqueCommitteeException {
//		System.out.println("Testing update");
//		Committee a = sampleCommittee;
//		Genre g = new Genre();
//		g.setId(2);
//		g.setName("Mystery");
//		a.setGenre(g);
//		try {
//			committeeDao.update(a);
//		} catch (NonUniqueCommitteeException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		Committee b = committeeDao.getById(a.getId());
//		assertEquals(b.getGenre(), g);
//	}
//	
//	@Order(6)
//	@Test
//	void testDelete() {
//		System.out.println("Testing delete");
//		committeeDao.delete(sampleCommittee);
//		assertFalse(committeeDao.getAll().contains(sampleCommittee));
//	}
//	
//	@Order(7)
//	@Test
//	void testPopulateCommittees() throws NonUniqueCommitteeException {
//		UserDAO userDao = new UserDAOFactory().getUserDao();
//		Random random = new Random();
//		for (int i = 1; i < 14; i++) {
//			Committee c = committeeDao.getById(i);
//			if (c != null) {
//				int count = 0;
//				while (count < 5) {
//					int rand = random.nextInt(45) + 46;
//					User u = userDao.getById(rand);
//					if (!c.getEditors().contains(u)) {
//						c.getEditors().add(u);
//						committeeDao.update(c);
//						count++;
//					}
//				}
//				count = 0;
//			
//			}
//		}
//
//	}
	
}
