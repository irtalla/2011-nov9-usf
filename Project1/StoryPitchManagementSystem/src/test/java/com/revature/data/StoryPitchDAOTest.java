package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.Type;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;
import com.revature.data.StoryPitchPostgreSQL;

import org.junit.jupiter.api.Test;

@TestMethodOrder(OrderAnnotation.class)
class StoryPitchDAOTest {
	
	private static StoryPitchDAO storyPitchDao;
	
	@BeforeAll 
	public static void setUp(){
		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
	}
	
	@Test
	@Order(1)
	void testGetById() {
		StoryPitch sp = null;
		sp = storyPitchDao.getByStoryPitchID(1);
		//System.out.println(sp);
		assertTrue(sp != null && sp.getStoryID() != 0);
	}
	
	@Test
	@Order(2)
	void testGetAll() {
		Set<StoryPitch> sp = null;
		sp = storyPitchDao.getAll();
		//System.out.println(sp);
		assertTrue(sp.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetByAuthorID() {
		Set<StoryPitch> sp = null;
		sp = storyPitchDao.getByAuthorID(28);
		//System.out.println(sp);
		assertTrue(sp.size() > 0);
	}
	
	@Test
	@Order(4)
	void testGetByGenreAndPriorityID() {
		Set<StoryPitch> sp = null;
		sp = storyPitchDao.getByPriorityAndGenreID(1,10);
		//System.out.println(sp);
		assertTrue(sp.size() > 0);
	}
	
	@Test
	@Order(5)
	void testGetByStatusID() {
		Set<StoryPitch> sp = null;
		sp = storyPitchDao.getByStatusID(1);
		//System.out.println(sp);
		assertTrue(sp.size() > 0);
	}
	
	
	@Test
	@Order(6)
	void testAddAndDelete() {
		StoryPitch sp =  new StoryPitch();
		sp.setTitle("Awesomebook");
		Type t = new Type();
		t.setTypeID(1);
		t.setName("Novel");
		sp.setType(t);
		//long millis=System.currentTimeMillis();  
		//java.sql.Date date=new java.sql.Date(millis); 
		String str = "2021-06-05";
		java.sql.Date strDate = Date.valueOf(str);
		//System.out.println(strDate);
		sp.setCompletionDate(strDate);
		sp.setTagline("Buy this book");
		sp.setDetailedDescription("This will be really good");
		sp.setPersonID(1);
		
		Status s = new Status();
		s.setStatusID(1);
		sp.setStatus(s);
		Genre g = new Genre();
		g.setGenreID(1);
		sp.setGenre(g);
		//sp.setPriorityID(1);
		sp.setChangesRequested(0);
		sp.setDraft("");
		
		storyPitchDao.add(sp);
		//System.out.println(sp);
		int id = 0;
		id = sp.getStoryID();
		assertTrue(id != 0);
		storyPitchDao.delete(sp);
		//System.out.println(personDao.getByPersonId(id));
		assertTrue(storyPitchDao.getByStoryPitchID(id) == null);
	}
	
	@Test
	@Order(7)
	void testUpdate() {
		StoryPitch sp = null;
		sp = storyPitchDao.getByStoryPitchID(1);
		sp.setDraft("test");
		storyPitchDao.update(sp);
		StoryPitch sp2 = null;
		sp2 = storyPitchDao.getByStoryPitchID(1);
		//System.out.println(sp2);
		assertTrue(sp2.getDraft().equals("test"));
		// undoing change
		sp2.setDraft("");
		storyPitchDao.update(sp2);
	}

}
