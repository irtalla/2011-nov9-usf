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
import com.revature.beans.Editor;
import com.revature.beans.Genre;
import com.revature.beans.LengthOfWork;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.User;
import com.revature.beans.Work;
import com.revature.dao.ApprovalProofreadDAO;
import com.revature.dao.FinalViewersDAO;
import com.revature.dao.FurtherInfoRequestsDAO;
import com.revature.dao.GenreCommitteeMemberDAO;
import com.revature.dao.StoryPitchDAO;
import com.revature.services.AuthorServicesImpl;
import com.revature.services.EditorServicesImpl;

@ExtendWith(MockitoExtension.class)
public class ApprovalDAOTest {

	@Mock
	static StoryPitchDAO spDAO;
	
	@Mock
	static ApprovalProofreadDAO apDAO;
	
	@Mock
	static FinalViewersDAO fvDAO;
	
	@Mock
	static GenreCommitteeMemberDAO gcmDAO;
	
	@Mock
	static FurtherInfoRequestsDAO firDAO;
	
	@InjectMocks
	static AuthorServicesImpl authorServ;
	
	@InjectMocks
	static EditorServicesImpl editorServ;
	
	Set<Editor> nullProofreaders = new HashSet<Editor>();
	Set<Editor> nullViewers = new HashSet<Editor>();
	
	@Test
	public void testEditorRejectionOfTests() {
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
		storyPitch.setStatus(status);
		
		Status rejectedStatus = new Status();
		status.setId(3);
		status.setStatus("rejected");
		
		StoryPitch rejectedStoryPitch = new StoryPitch();
		storyPitch.setProposedWork(workAngelica);
		storyPitch.setDateWhenStageStarted(date);
		storyPitch.setStage(stage);
		storyPitch.setStatus(rejectedStatus);
		
		when(spDAO.rejectAPitch(storyPitch)).thenReturn(true);
		when(spDAO.retrieveAPitch(storyPitch.getProposedWork().getAuthor().getAuthorId(), storyPitch.getProposedWork().getId())).thenReturn(rejectedStoryPitch);
		assertTrue(spDAO.rejectAPitch(storyPitch));
		assertNotEquals(spDAO.retrieveAPitch(storyPitch.getProposedWork().getAuthor().getAuthorId(), storyPitch.getProposedWork().getId()).getStatus(), storyPitch.getStage());
		verify(spDAO).rejectAPitch(storyPitch);
		verify(spDAO).retrieveAPitch(storyPitch.getProposedWork().getAuthor().getAuthorId(), storyPitch.getProposedWork().getId());
		
		
		when(apDAO.removeProofreaders(storyPitch.getId())).thenReturn(true);
		when(fvDAO.removeViewers(storyPitch.getId())).thenReturn(true);
		assertTrue(apDAO.removeProofreaders(storyPitch.getId()));
		assertTrue(fvDAO.removeViewers(storyPitch.getId()));
		verify(apDAO).removeProofreaders(storyPitch.getId());
		verify(fvDAO).removeViewers(storyPitch.getId());
	}
}
