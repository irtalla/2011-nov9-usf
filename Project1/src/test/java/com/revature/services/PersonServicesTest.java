package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServicesTest {
	public static PersonServiceImpl personService;
	public static GenreService genreService;
	
	@BeforeAll
	public void initializeService() {
		personService = new PersonServiceImpl();
		genreService = new GenreServiceImpl();
	}
	
	@BeforeEach
	public void initializeTestSubjects() {
		Genre sciFi = genreService.getByName("sciFi");
	}
	
	@Test
	public void testUniqueUsernames() throws NonUniqueUsernameException {
		Person testAuthor = new Person();
		testAuthor.setUsername("test author");
		testAuthor.setPassword("pass");
		testAuthor = personService.add(testAuthor);
		
		//assert that adding a user with a duplicate username throws exception
		Person duplicate = new Person();
		duplicate.setUsername("test author");
		duplicate.setPassword("pass");
		assertThrows(NonUniqueUsernameException.class, () -> personService.addPerson(duplicate));
	}
	
	@Test
	public void testAsstAndSeniorEditorsMustHaveAGenreSpecialty() {
		Person testAsstEditor = new Person();
		testAsstEditor.setUsername("test asst editor");
		testAsstEditor.setPassword("pass");
//		testAsstEditor.setGenreSpecialty(testGenre);
		assertThrows(GenreEditorWithoutGenreSpecialtyException.class, () -> testAsstEditor = personService.add(testAsstEditor));
		
		testAsstEditor.setGenreSpecialty(sciFi);
		testAsstEditor = personService.add(testAsstEditor);
		assertNotNull(testAsstEditor);
		
		//same for senior editor:
		Person testSeniorEditor = new Person();
		testSeniorEditor.setUsername("test asst editor");
		testSeniorEditor.setPassword("pass");
//		testSeniorEditor.setGenreSpecialty(testGenre);
		assertThrows(GenreEditorWithoutGenreSpecialtyException.class, () -> testSeniorEditor = personService.add(testSeniorEditor));
		
		testSeniorEditor.setGenreSpecialty(sciFi);
		testSeniorEditor = personService.add(testSeniorEditor);
		assertNotNull(testSeniorEditor);
	}
	
	@Test
	public void testGeneralEditorMayNotHaveAGenreSpecialty() {
		Person testGeneralEditor= new Person();
		testGeneralEditor.setUsername("test general editor");
		testGeneralEditor.setPassword("pass");
		testGeneralEditor.setGenreSpecialty(sciFi);
		assertThrows(GeneralEditorWithGenreSpecialtyException.class, () -> personService.add(testGeneralEditor));
	}
}
