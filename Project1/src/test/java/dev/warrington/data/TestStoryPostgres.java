package dev.warrington.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Person;
import dev.warrington.beans.Role;
import dev.warrington.beans.Story;
import dev.warrington.exceptions.NonUniqueUsernameException;

public class TestStoryPostgres {
	
	@Test
	public void getMyStories() throws NonUniqueUsernameException {
		Set<Story> stories = new HashSet<Story>();
		PersonPostgres personTest = new PersonPostgres();
		Person p2 = new Person();
		Role r = new Role();
		r.setId(1);
		r.setName("author");
		p2.setFirstName("test");
		p2.setLastName("test");
		p2.setPassHash("test");
		p2.setUsername("test");
		p2.setSalt("test");
		p2.setRole(r);
		Story s = new Story();
		Person person = personTest.add(p2);
		s.setTitle("test");
		s.setAuthorId(person.getId());
		s.setGenre(1);
		s.setStoryType(1);
		s.setTagline("test");
		s.setDescription("test");
		s.setStatus(2);
		s.setPriority(0);
		s.setFilePath("");
		s.setSubmissionDate(LocalDate.parse("2020-10-05"));
		s.setCompletionDate("2020-01-01");
		s.setNote("test");
		s.setRequest(false);
		
		StoryPostgres storyTest = new StoryPostgres();
		
		storyTest.addStory(s);
		stories = storyTest.getMyStories(person.getId(), person.getRole().getId());
		
		assertEquals(stories.size(), 1);
		
		for (Story story : stories) {
			storyTest.delete(story.getId());
		}
		
		personTest.delete(person.getId());
	}

}