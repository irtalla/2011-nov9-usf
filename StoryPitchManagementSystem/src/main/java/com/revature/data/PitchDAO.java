package com.revature.data;

import java.util.Set;

import com.revature.beans.Pitch;

public interface PitchDAO extends GenericDAO<Pitch> {
	public Pitch add(Pitch p);
}