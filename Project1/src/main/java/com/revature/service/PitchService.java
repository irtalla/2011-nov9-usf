package com.revature.service;

import java.util.Set;

import com.revature.beans.Pitch;

public interface PitchService {
	
	public Integer addPitch(Pitch p);
	
	public Pitch getPitchById(Integer id);
	public Set<Pitch> getAllPitchesByPersonId (Integer id);

	public void updatePitch (Pitch p);
	public void deletePitch (Pitch p);
}
