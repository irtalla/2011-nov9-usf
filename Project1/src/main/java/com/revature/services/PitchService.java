package com.revature.services;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.exceptions.AdditionalPitchWithPendingStatusException;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.SavedPitchChangedToPendingWithoutEnoughPointsException;

public interface PitchService {
	//crud methods:
	public Pitch addPitch(Pitch p) throws 
		AdditionalPitchWithPendingStatusException,
		NonAuthorHasPitchesException;
	// read
	public Pitch getPitchById(Integer id);
	// update
	public Pitch updatePitch(Pitch p) throws 
		NonAuthorHasPitchesException,
		SavedPitchChangedToPendingWithoutEnoughPointsException;
	// delete
	public void deletePitch(Pitch p);
	public Set<Pitch> getAll();	
}
