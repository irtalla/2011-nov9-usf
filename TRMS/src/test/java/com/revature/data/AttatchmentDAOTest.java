package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.EventAttatchment;

public class AttatchmentDAOTest {

	@Test
	public void testAddGetDelete()
	{
		Path path = Paths.get("D:/Testing/Java/Hi.txt");
		EventAttatchment f = new EventAttatchment();
		f.setName("Hi.txt");
		f.setEventId(1);
		try {
			f.setData(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
			return;
		}
		
		
		DAOFactory.getAttatchmentDAO().add(f);
		assertTrue(f.getId() != -1);
		
		Set<EventAttatchment> f3 = DAOFactory.getAttatchmentDAO().getEventAttatchmentByEventId(f.getEventId());
		assertTrue(f3.contains(f));
		
		DAOFactory.getAttatchmentDAO().delete(f);
		f3 = DAOFactory.getAttatchmentDAO().getEventAttatchmentByEventId(f.getEventId());
		assertFalse(f3.contains(f));
	}
}
