package com.revature.data;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Pitch;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.SurplusPitchException;

public interface PitchDAO extends GenericDAO<Pitch> {
	Pitch addPitch(Pitch p) throws 
		SurplusPitchException, 
		NonAuthorHasPitchesException;
	Pitch updatePitch(Pitch p) throws SurplusPitchException;
	Set<Pitch> getAllPitchesWithGenre(Genre g);
}
