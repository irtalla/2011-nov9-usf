package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.awaitility.Awaitility.*;
import static java.time.Duration.*;
import static java.util.concurrent.TimeUnit.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.exceptions.UnexplainedDenialException;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.exceptions.OutsideGenreSpecialtyException;


public class PitchProcessTest {
	public PersonService personService;
	public GenreService genreService;
	public PitchService pitchService;
	public PitchFeedbackService pitchFeedbackService;
	public DraftService draftService;
	public EnumService enumService;
	
	private Role authorRole;
	private Role asstEditorRole;
	private Role generalEditorRole;
	private Role seniorEditorRole;
	
	private Person testAuthor;
	private Person testAsstEditor;
	private Person testSeniorEditor;
	private Person testGeneralEditor;
	private Genre testGenre;
	private Pitch testPitch;
	
	@BeforeAll
	public void initializeServices() {
		personService = new PersonServiceImpl();
		genreService = new GenreServiceImpl();
		pitchService = new PitchServiceImpl();
		pitchFeedbackService = new PitchFeedbackServiceImpl();
		draftService = new DraftServiceImpl();
		enumService = new EnumServiceImpl();
	}
	
	@BeforeEach
	public void initializeTestSubjects() {
		authorRole = new Role();
		asstEditorRole = new Role();
		generalEditorRole = new Role();
		seniorEditorRole  = new Role();
		
		testAuthor = new Person();
		testAuthor.setRole(authorRole);
		testAuthor.setUsername("test author");
		testAuthor.setPassword("pass");
		testAuthor = personService.add(testAuthor);
		
		testGenre = genreService.getByName("YA");
		
		testPitch = new Pitch();
		testPitch.setAuthor(testAuthor);
		testPitch.setGenre(testGenre);
		testPitch.setTentativeTitle("test title");
		testPitch.setTagLine("test tag line");
		testPitch.setDescription("test description");
		//not specifying a storyType just yet, so
		//not adding testPitch to db just yet
		
		testAsstEditor= new Person();
		testAsstEditor.setRole(asstEditorRole);
		testAsstEditor.setUsername("test asst editor");
		testAsstEditor.setPassword("pass");
		testAsstEditor.setGenreSpecialty(testGenre);
		testAsstEditor = personService.add(testAsstEditor);
		
		testGeneralEditor = new Person();
		testGeneralEditor.setRole(generalEditorRole);
		testGeneralEditor.setUsername("test general editor");
		testGeneralEditor.setPassword("pass");
		testGeneralEditor = personService.add(testGeneralEditor);
		
		testSeniorEditor= new Person();
		testSeniorEditor.setRole(seniorEditorRole);
		testSeniorEditor.setUsername("test senior editor");
		testSeniorEditor.setPassword("pass");
		testSeniorEditor.setGenreSpecialty(testGenre);
		testSeniorEditor = personService.add(testSeniorEditor);
	}
	
	@Test
	public void testAuthorMayNotGiveFeedbackRegardingPitch() {
		//assert that exception is thrown when authors attempt to give feedback on a pitch or draft:
		PitchFeedback feedback = new PitchFeedback();
		feedback.setPitch(testPitch);
		feedback.setEditor(testAuthor);
		assertThrows(FeedbackAsAuthorException.class, () -> pitchFeedbackService.addPitchFeedback(feedback));
	}

	@Test
	public void testPitchProcessConstraints() {
		//assert that a newly created author has 100 points remaining
		Integer pointsRemainingBeforeFirstNovel = personService.getPointsRemaining(testAuthor);
		assertNotNull(pointsRemainingBeforeFirstNovel);
		assertEquals(pointsRemainingBeforeFirstNovel, 100);
		
		//assert that author with 100 points remaining can pitch a new novel that will be considered
		StoryType novel = enumService.getByNameFromTable("novel", "story_type");
		testPitch.setStoryType(novel);
		
		testPitch = pitchService.add(testPitch);
		assertNotNull(addedPitch);
		
		//assert that author has 50 points remaining after pitching novel:
		Integer pointsRemainingAfterFirstNovel = personService.getPointsRemaining(testAuthor);
		assertEquals(pointsRemainingAfterFirstNovel, 50);

		//assert that this new pitch has normal priority
		Priority normalPriority = enumService.getByNameFromTable("normal", "priority");
		assertTrue(addedPitch.getPriority().equals(normalPriority));		
				
		//assert that this new pitch has a "pending" status
		Status pending = enumService.getByNameFromTable("pending", "status");
		assertTrue(addedPitch.getStatus().equals(normalPriority));		
		
		//assert that this new pitch appears in the pending pitches of the appropriate assistant genre-editors
		Set<Person> asstEditorsForGenre = genreService.getAssistantEditorsForGenre(testGenre);
		for(Person asstEditor : asstEditorsForGenre) {
			assertTrue(personService.getPitchesAvailableForConsiderationBy(asstEditor).contains(testPitch));
		}
		
		//assert that a pitch becomes high priority for genre-editors after appropriate amount of time for that story-type
		Priority highPriority = enumService.getByNameFromTable("high", "priority");
		await().atLeast(novel.getWaitTimeForPriorityChange(), SECONDS).until(() -> pitchService().priority == highPriority);
		assertTrue(testPitch.getPriority().equals(highPriority));
		
		//add a second novel to exhaust points
		Pitch secondPitch = new Pitch();
		secondPitch.setAuthor(testAuthor);
		secondPitch.setGenre(testGenre);
		secondPitch.setTentativeTitle("test title");
		secondPitch.setTagLine("test tag line");
		secondPitch.setDescription("test description");
		secondPitch.setStoryType(novel);
		
		secondPitch = pitchService.addPitch(secondPitch);
		
		//assert that author has no points remaining
		assertTrue(personService.getRemainingPointsForAuthor(testAuthor) == 0);
		
		//assert that a subsequent pitch will be given the status "saved"
		Pitch thirdPitch = new Pitch();
		thirdPitch.setAuthor(testAuthor);
		thirdPitch.setGenre(testGenre);
		thirdPitch.setTentativeTitle("test title");
		thirdPitch.setTagLine("test tag line");
		thirdPitch.setDescription("test description");
		thirdPitch.setStoryType(novel);
		
		thirdPitch = pitchService.addPitch(thirdPitch);
		
		Status saved = enumService.getByNameFromTable("saved", "status");
		assertTrue(thirdPitch.getStatus().equals(saved));
		
		//and will not appear in the list of pitches for the assistant editors for this genre
		for(Person asstEditor : asstEditorsForGenre) {
			assertFalse(personService.getPitchesAvailableForConsiderationBy(asstEditor).contains(thirdPitch));
		}
		
		//assert that an editor cannot approve pitches with a different genre
		Genre sciFi = genreService.getByName("sciFi");
		Person asstSciFiEditor = new Person();
		asstSciFiEditor.setUsername("sci fi asst editor");
		asstSciFiEditor.setPassword("pass");
		asstSciFiEditor.setGenreSpecialty(sciFi);
		asstSciFiEditor = personService.add(asstSciFiEditor);
		
		assertThrows(OutsideGenreSpecialtyException.class, () -> personService.approvePitchBy(testPitch, asstSciFiEditor));
		
		//assert that denying a pitch without an explanation throws an error
		Status denied = enumService.getByNameFromTable("denied", "status");
		
		PitchFeedback denial = new PitchFeedback();
		denial.setEditor(testAsstEditor);
		denial.setPitch(testPitch);
		denial.setTarget(testAuthor);
		denial.setStatus(denied);
		
		assertThrows(UnexplainedDenialException.class, () -> pitchFeedbackService.addPitchFeedback(denial));
		
		//assert that explained denials update the status of the denied pitch
		//and remove the pitch from the list of pitches for assistant editors in that genre to consider
		denial.setExplanation("Boring");
		denial = pitchFeedbackService.addPitchFeedback(denial);
		assertNotNull(denial);
		
		testPitch = pitchService.getById(testPitch.getId());

		//note: a pitch's status should be computed, rather than stored as a field, since
		//the status of a pitch is really represented through the status assigned to a pitch
		//in instances of pitch feedback
		Status updatedStatus = pitchService.getStatusOfPitchWithId(testPitch.getId());
		assertTrue(updatedStatus.equals(denied));
		
		for(Person asstEditor : asstEditorsForGenre) {
			assertFalse(personService.getPitchesAvailableForConsiderationBy(asstEditor).contains(testPitch));
		} 
		
		//assert that the author is notified of denial feedback
		assertTrue(pitchService.getAllFeedbackForPitch(testPitch).contains(denial));
	}
	
	@Test
	public void testPitchApprovalProcess() {
		StoryType novel = enumService.getByNameFromTable("novel", "story_type");
		testPitch.setStoryType(novel);
		
		testPitch = pitchService.add(testPitch);
		
		//assert that approval by an assistant editor for this genre
		//does not change the overall status of the pitch
		testPitch = personService.approvePitchBy(testPitch, testAsstEditor);
		
		Status pending = enumService.getByNameFromTable("pending", "status");
		
		assertFalse(testPitch.getStatus().equals(approved)); //not fully approved yet! Still pending!
		assertTrue(testPitch.getStatus().equals(pending));
		
		//assert that approval by an assistant editor for this genre
		//makes the pitch available for consideration by general editors
		assertTrue(pitchService.getPitchesAvailableForConsiderationBy(testGeneralEditor).contains(testPitch));		
		
		//assert that approval by a general editor
		//does not change the overall status of the pitch
		testPitch = personService.approvePitchBy(testPitch, testGeneralEditor);
		assertTrue(testPitch.getStatus().equals(pending));
		
		//assert that creating a draft from this pitch is disallowed prior to full-approval:
		Draft testDraft = new Draft();
		testDraft.setPitch(testPitch); 
		
		assertThrows(DraftFromUnapprovedPitchException.class, () -> testDraft = draftService.addDraft(testDraft)); 
		
		//assert that approval by general editor makes the pitch available for consideration by senior editors
		assertTrue(pitchService.getPitchesAvailableForConsiderationBy(testSeniorEditor).contains(testPitch));		
		
		//assert that approval by a senior editor for this genre
		//changes the status of the pitch to "approved"
		testPitch = personService.approvePitchBy(testPitch, testSeniorEditor);
		
		Status approved = enumService.getByNameFromTable("approved", "status");
		assertTrue(testPitch.getStatus().equals(approved));
		
		Draft testDraft = new Draft();
		testDraft.setPitch(testPitch); 
		
		testDraft = draftService.addDraft(testDraft); //if no error is thrown, test will pass!
		assertNotNull(testDraft);
		
		//assert that the status of the draft is "pending"
		assertTrue(testDraft.getStatus().equals(pending));
	}
}
