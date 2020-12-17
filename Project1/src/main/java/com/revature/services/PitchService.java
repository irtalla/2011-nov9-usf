package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Pitch;

public interface PitchService extends GenericService<Pitch>{

	Set<Pitch> getPitchesViewableBy(Person person);

	Set<Pitch> getAllPitchesWithGenre(Genre genre);

}
