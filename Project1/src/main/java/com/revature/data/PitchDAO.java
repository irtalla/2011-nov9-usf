package com.revature.data;

import com.revature.beans.Person;
import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.beans.Status;

public interface PitchDAO extends GenericDAO<Pitch> {
	public Pitch add(Pitch p);
	public Set<Pitch> getByStatus(String status);
	public Set<Pitch> getByAuthor(Person author);
}
