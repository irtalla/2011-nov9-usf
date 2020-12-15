package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.Role;
import com.revature.models.StoryType;
import com.revature.models.User;

@TestMethodOrder(OrderAnnotation.class)
class PitchDAOTest {
	private static PitchDAO pitchDao;
	private static Pitch samplePitch;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PitchDAOFactory pitchFactory = new PitchDAOFactory();
		pitchDao = pitchFactory.getPitchDao();
		
		samplePitch = new Pitch();
		samplePitch.setId(0);
		User u = new User();
		u.setId(1);
		u.setFirstName("Arlo");
		u.setLastName("Dominguez");
		u.setEmail("Arlo.Dominguez@revature.net");
		u.setUsername("SeniorEdit_1");
		u.setPassword("password");
		Role r = new Role();
		r.setId(4);
		r.setName("EditorIII");
		u.setRole(r);
		samplePitch.setAuthor(u);
		samplePitch.setTitle("Sample Title");
		samplePitch.setTagline("Sample Tagline");
		StoryType st = new StoryType();
		st.setId(1);
		st.setWeight(10);
		st.setName("Article");
		samplePitch.setStoryType(st);
		Genre g = new Genre();
		g.setId(1);
		g.setName("Romance");
		samplePitch.setGenre(g);
		samplePitch.setDescription("Sample Description");
		samplePitch.setCompletionDate(LocalDate.now());
		samplePitch.setPitchMadeAt(LocalDateTime.now());
		samplePitch.setPriority(Priority.NORMAL);
		PitchStage ps = new PitchStage();
		ps.setId(1);
		ps.setName("Submitted");
		samplePitch.setPitchStage(ps);
		ReviewStatus rs = new ReviewStatus();
		rs.setId(1);
		rs.setName("On Hold");
		samplePitch.setReviewStatus(rs);
		
	}
	
	@Order(1) 
	@Test
	void testAdd() {
		try {
			Integer newId = pitchDao.add(samplePitch);
			assertNotEquals(newId, 0);
			samplePitch.setId(newId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test
	void testGetById() {
		System.out.println("Testing id");
		Pitch a = pitchDao.getById(samplePitch.getId());
		assertEquals(a, samplePitch);
	}

	@Order(3)
	@Test
	void testGetByAuthor() {
		System.out.println("Testing author");
		Set<Pitch> a = pitchDao.getByAuthor(samplePitch.getAuthor());
		assertTrue(a.contains(samplePitch));
	}
	
	@Order(4)
	@Test
	void testGetByGenre() {
		System.out.println("Testing genre");
		List<Genre> list = new ArrayList<>();
		list.add(samplePitch.getGenre());
		Set<Pitch> a = pitchDao.getByGenre(true, list);
		assertTrue(a.contains(samplePitch));
		a = pitchDao.getByGenre(false, list);
		assertNull(a);
	}
	
	@Order(5)
	@Test
	void testGetByStoryType() {
		System.out.println("Testing type");
		Set<Pitch> a = pitchDao.getByStoryType(samplePitch.getStoryType());
		assertTrue(a.contains(samplePitch));
	}
		
	@Order(6)
	@Test
	void testGetByPitchStage() {
		System.out.println("Testing stage");
		Set<Pitch> a = pitchDao.getByPitchStage(samplePitch.getPitchStage());
		assertTrue(a.contains(samplePitch));
	}
	
	@Order(7)
	@Test
	void getByReviewStatus() {
		System.out.println("Testing status");
		Set<Pitch> a = pitchDao.getByReviewStatus(samplePitch.getReviewStatus());
		assertTrue(a.contains(samplePitch));
	}
	
	@Order(8)
	@Test
	void testGetByPriority() {
		System.out.println("Testing priority");
		Set<Pitch> a = pitchDao.getByPriority(samplePitch.getPriority());
		assertTrue(a.contains(samplePitch));
		a = pitchDao.getByPriority(Priority.HIGH);
		assertFalse(a.contains(samplePitch));
	}
	
	@Order(9)
	@Test
	void testGetAll() {
		System.out.println("Testing all");
		Set<Pitch> a = pitchDao.getAll();
		assertTrue(a.contains(samplePitch));
	}
	
	@Order(10)
	@Test
	void testUpdate() {
		System.out.println("Testing update");
		Pitch a = samplePitch;
		Genre g = new Genre();
		g.setId(2);
		g.setName("Mystery");
		a.setGenre(g);
		
		try {
			pitchDao.update(samplePitch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Pitch b = pitchDao.getById(a.getId());
		assertEquals(b.getGenre(), g);
		
	}
	
	@Order(11)
	@Test
	void testDelete() {
		System.out.println("Testing delete");
		pitchDao.delete(samplePitch);
		assertFalse(pitchDao.getAll().contains(samplePitch));
	}
}
