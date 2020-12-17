package com.revature.services;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Genre;
import com.revature.beans.Person;

public class DraftProcessTest {
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
	public void testNovelDraftProcess() {
		StoryType novel = enumService.getByNameFromTable("novel", "story_type");
		
		Draft testDraft = createDraft(novel);
		
		//create additional senior editor to enable creation of majority on genre committee
		Person seniorEditor2  = new Person();
		seniorEditor2.setRole(seniorEditorRole);
		seniorEditor2.setUsername("senior editor 2");
		seniorEditor2.setPassword("pass");
		
		//ensure that editor with different genre specialization cannot view this draft
		Genre youngAdult = genreService.getByName("YA");
		seniorEditor2.setGenreSpecialty(youngAdult);
		seniorEditor2 = personService.addPerson(seniorEditor2);
		
		Set<Draft> draftsToReview = personService.getDraftsAvailableForReviewBy(seniorEditor2);
		assertFalse(draftsToReview.contains(testDraft));
		
		//ensure that editor with same genre specialization can view the draft
		seniorEditor2.setGenreSpecialty(sciFi);
		seniorEditor2 = personService.update(seniorEditor2);
		
		draftsToReview = personService.getDraftsAvailableForReviewBy(seniorEditor2);
		assertTrue(draftsToReview.contains(testDraft));
		
		//assert that general editor that approved pitch can review draft
		draftsToReview = personService.getDraftsAvailableForReviewBy(testGeneralEditor);
		assertTrue(draftsToReview.contains(testDraft));
		
		//assert that draft is not fully approved until approved by
		//a majority of members in the genre committee
		personService.approveDraftBy(testDraft, testAsstEditor);
		personService.approveDraftBy(testDraft, testSeniorEditor);
		
		testDraft = draftSerivce.getDraftById(testDraft);
		
		Status approved = enumService.getByNameFromTable("approved", "status");
		assertFalse(testDraft.getStatus().equals(approved));
		
		personService.approveDraftBy(testDraft, seniorEditor2);
		
		assertTrue(testDraft.getStatus().equals(approved));
	}
	
	public Draft createDraft(StoryType storyType) {
		testPitch.setStoryType(storyType);
		testPitch = pitchService.addPitch(testPitch);
		
		testPitch = personService.approvePitchAsEditor(testPitch, testAsstEditor);
		testPitch = personService.approvePitchAsEditor(testPitch, testGeneralEditor);
		testPitch = personService.approvePitchAsEditor(testPitch, testSeniorEditor);
		
		Draft testDraft = new Draft();
		testDraft.setPitch(testPitch);
		
		return testDraft;
	}
}
