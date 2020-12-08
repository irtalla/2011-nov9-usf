package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.exceptions.InvalidCommitteeSizeException;

public class GenreCommitteeServicesTest {
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
	public void testGenreCommitteMembershipRequirements() {
		Genre sciFi = genreService.getByName("sciFi");
		GenreCommitte sciFiCommittee = new GenreCommittee();
		sciFiCommittee.setGenre(sciFi);
		
		Set<Person> members = new HashSet<>();
		sciFiCommittee.setMembers(members);
		
		//assert that creating a committee with no members will throw an error:
		assertThrows(InvalidCommitteeSizeException.class, () -> genreService.addGenreCommittee(sciFiCommittee));
		
		//assert that creating a committee with only 2 senior editors and no one else
		//throws an error
		members.add(testSeniorEditor);
		Person seniorEditor2  = new Person();
		seniorEditor2.setRole(seniorEditorRole);
		seniorEditor2.setUsername("senior editor 2");
		seniorEditor2.setPassword("pass");
		
		
		sciFiCommittee.setMembers(members);
		
		assertThrows(InvalidCommitteeSizeException.class, () -> genreService.addGenreCommittee(sciFiCommittee));
		
		//assert that creating a committee with over two senior editors will throw an error
		Person seniorEditor3  = new Person();
		seniorEditor3.setRole(seniorEditorRole);
		seniorEditor3.setUsername("senior editor 3");
		seniorEditor3.setPassword("pass");
		
		members.add(seniorEditor3);
		
		sciFiCommittee.setMembers(members);
		
		assertThrows(InvalidCommitteeSizeException.class, () -> genreService.addGenreCommittee(sciFiCommittee));
		
		members.remove(seniorEditor3);
		
		//assert that creating a committee with an author will throw an error:
		members.add(testAuthor);
		
		sciFiCommittee.setMembers(members);
		assertThrows(InvalidCommitteeSizeException.class, () -> genreService.updateGenreCommittee(sciFiCommittee));
				
		members.remove(testAuthor);
		
		//even though general editor contributed to pitch with this genre,
		//assert that he may not join this committee
		members.add(testGeneralEditor);
		
		sciFiCommittee.setMembers(members);
		assertThrows(InvalidCommitteeSizeException.class, () -> genreService.updateGenreCommittee(sciFiCommittee));
				
		members.remove(testGeneralEditor);
		
		//assert that a genre committee must have at least three members to be valid, only two of whom can be senior editors
		
	}
}
