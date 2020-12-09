package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		u.setEmail("Arlo.Dominguez@@revature.net");
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
		st.setName("Article");
		samplePitch.setStoryType(st);
		Genre g = new Genre();
		g.setId(1);
		g.setName("Romance");
		samplePitch.setGenre(g);
		samplePitch.setDescription("Sample Description");
		samplePitch.setCompletionDate(LocalDate.now());
		samplePitch.setPitchMadeAt(LocalDateTime.now());
		samplePitch.setPriority(Priority.NOMRAL);
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
		
	}

}
