package com.revature.data;

import com.revature.beans.Pitch;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.SurplusPitchWithPendingStatusException;

public interface PitchDAO extends GenericDAO<Pitch> {
	Pitch addPitch(Pitch p) throws 
		SurplusPitchWithPendingStatusException, 
		NonAuthorHasPitchesException;
	Pitch updatePitch(Pitch p) throws SurplusPitchWithPendingStatusException;
}
