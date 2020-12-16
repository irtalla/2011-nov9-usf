package com.revature.services;

import com.revature.beans.Request;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Status;

public interface PitchService {
	// create
	public Integer addPitch(Pitch pitch, Person author);
	// read
	public Pitch getPitchById(Integer id);
	public Set<Pitch> getPitches();
	public Set<Pitch> getPitchesByStatus(String status);
	public Set<Pitch> getPitchesByAuthor(Person author);
	public Boolean reviewApprovals(Pitch pitch);
	// update
	public void updatePitch(Pitch p);
	public void approvePitch(Pitch pitch, Person person);
	public void rejectPitch(Pitch pitch, Person person, String editorNotes);
	// delete
	public void removePitch(Pitch p);
}
