package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Pitch;
import com.revature.beans.Storytype;

public interface PitchService {
	// create
		public Integer addPitch(Pitch p);
		// read
		public Pitch getPitchById(Integer id);
		// update
		public void updatePitch(Pitch p);
		// delete
		public void deletePitch(Pitch p);
		public Set<Pitch> getAll();
		public Set<Genre> getGenres();
		public Set<Storytype> getTypes();
}
