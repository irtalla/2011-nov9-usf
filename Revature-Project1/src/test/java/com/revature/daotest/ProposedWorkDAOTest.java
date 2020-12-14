package com.revature.daotest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Author;
import com.revature.beans.Genre;
import com.revature.beans.LengthOfWork;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.beans.Work;
import com.revature.dao.ProposedWorkDAO;
import com.revature.dao.StoryPitchDAO;
import com.revature.services.AuthorServicesImpl;

@ExtendWith(MockitoExtension.class)
public class ProposedWorkDAOTest {

	@Mock
	static StoryPitchDAO spDAO;
	
	@Mock
	static ProposedWorkDAO pwDAO;
	
	@InjectMocks
	static AuthorServicesImpl authorServ;
	
	Set<Work> workList = new HashSet<Work>();
	
	@Test
	public void testAddWork() {
		String username = "palimpsest";
		String password = "sortiedVesenrico";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setId(1);
		
		Author author = new Author();
		author.setAuthorId(1);
		author.setName("Julenpop Gasbeam");
		author.setUserInfo(user);
		
		Genre genre = new Genre();
		genre.setId(9);
		genre.setGenreName("young adult");
		
		Calendar calendarDate = new Calendar.Builder().setCalendarType("iso8601").setWeekDate(2020, 12, 3).build();
		Date date = new Date(calendarDate.getTime().getTime());
		
		LengthOfWork lengthOfWork = new LengthOfWork();
		lengthOfWork.setId(3);
		lengthOfWork.setName("novella");
		lengthOfWork.setAssociatedPoints(25);
		
		Work workAngelica = new Work();
		workAngelica.setAuthor(author);
		workAngelica.setGenre(genre);
		workAngelica.setTitle("Weekend Breakdown");
		workAngelica.setDescription("What else is there to say about this weekend?");
		workAngelica.setTentativeCompletionDate(date);
		workAngelica.setId(77);
		workAngelica.setLengthOfWork(lengthOfWork);
		
		Work workEliza = new Work();
		workEliza.setAuthor(author);
		workEliza.setGenre(genre);
		workEliza.setTitle("Weekend Breakdown");
		workEliza.setDescription("What else is there to say about this weekend?");
		workEliza.setTentativeCompletionDate(date);
		workEliza.setId(77);
		workEliza.setLengthOfWork(lengthOfWork);
		
		Stage stage = new Stage();
		stage.setStageName("assistant editor");
		stage.setId(1);
		
		Status status = new Status();
		status.setId(1);
		status.setStatus("pending");
		
		StoryPitch storyPitch = new StoryPitch();
		storyPitch.setProposedWork(workAngelica);
		storyPitch.setDateWhenStageStarted(date);
		storyPitch.setStage(stage);
		
		
		StoryPitch duplicate = new StoryPitch();
		duplicate.setProposedWork(workAngelica);
		duplicate.setDateWhenStageStarted(date);
		duplicate.setStage(stage);
		
		
		when(spDAO.addStoryPitch(storyPitch)).thenReturn(duplicate);
		assertEquals(spDAO.addStoryPitch(storyPitch), duplicate);
		verify(spDAO).addStoryPitch(storyPitch);
		
		when(pwDAO.addProposedWork(workAngelica)).thenReturn(workEliza);
		assertEquals(pwDAO.addProposedWork(workAngelica), workEliza);
		verify(pwDAO).addProposedWork(workAngelica);
	}
	
	
	@Test
	public void testFetchingAllUsersWork() {
		String username = "palimpsest";
		String password = "sortiedVesenrico";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setId(1);
		
		Author author = new Author();
		author.setAuthorId(1);
		author.setName("Julenpop Gasbeam");
		author.setUserInfo(user);
		
		when(pwDAO.getAllWorksOfAuthor(author.getAuthorId())).thenReturn(workList);
		assertEquals(pwDAO.getAllWorksOfAuthor(author.getAuthorId()), workList);
		verify(pwDAO).getAllWorksOfAuthor(author.getAuthorId());
	}
	
	
}
