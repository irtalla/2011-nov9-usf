package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Genre;
import com.revature.beans.Pitch;
import com.revature.beans.Status;
import com.revature.beans.Storytype;
import com.revature.data.PitchDAO;
import com.revature.data.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class PitchServiceTest {

	@Mock
	static PitchDAO pitchDao;
	
	@Mock
	static PersonDAO personDao;
	
	@InjectMocks
	static PitchServiceImpl pitchServ;
	
	static Set<Pitch> pitchesMock = new HashSet<>();
	static Integer pitchSequenceMock = 1;
	
	@Test
	public void testAddValidPitch() {
		Pitch p = new Pitch();
		Genre g = new Genre();
		Storytype t = new Storytype();
		g.setId(1);
		t.setId(1);
		Status s = new Status();
		s.setId(1);
		p.setGenre(g);
		p.setPitchtype(t);
		p.setStatus(s);
		
		pitchesMock.add(p);
		Pitch p2 = new Pitch();
		p2.setId(pitchSequenceMock++);
		p2.setGenre(p.getGenre());
		p2.setPitchtype(p.getPitchtype());
		p2.setStatus(p.getStatus());
		when(pitchDao.add(p)).thenReturn(p2);
		assertNotEquals(p.getId().intValue(), pitchServ.addPitch(p).intValue());
		verify(pitchDao).add(p);
	}
	
	@Test
	public void testGetPitches() {
		when(pitchDao.getAll()).thenReturn(pitchesMock);
		assertEquals(pitchesMock, pitchServ.getAll());
		verify(pitchDao).getAll();
	}
	
	@Test
	public void testGetPitchById() {
		Pitch p = new Pitch();
		p.setId(10);
		pitchesMock.add(p);
		
		when(pitchDao.getById(10)).thenReturn(p);
		assertEquals(p, pitchServ.getPitchById(10));
		verify(pitchDao).getById(10);
	}
	
	@Test
	public void testUpdatePitch() {
		Pitch p = new Pitch();
		pitchServ.updatePitch(p);
		verify(pitchDao).update(p);
	}
	
}
