package com.revature.services;

import java.util.Set;

import com.cross.beans.Pitch;

public interface PitchService {
	
	
	Set<Pitch> getPitchesByAuthorId(Integer id); 
	public Pitch addPitch(Pitch p);
	public Set<Pitch> getAll(); 
	public Pitch getPitchById(Integer id);
	public void updatePitch(Pitch p);
	public boolean deletePitch(Pitch p);

}
