package com.revature.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
import com.revature.data.PitchDAOFactory;
import com.revature.data.PitchHibernate;

public class PitchServiceImpl extends GenericServiceImpl<Pitch> implements PitchService {

	public PitchServiceImpl() {
		super(new PitchDAOFactory());	
	}

	@Override
	PitchHibernate getDao() {
		return new PitchHibernate();
	}

	@Override
	public Set<Pitch> getPitchesViewableBy(Person person) {
		return getDao().getPitchesViewableBy(person);
//		Role role = person.getRole();
//		switch(role) {
//			case AUTHOR:
//				return person.getPitches(); //authored pitches
//			case GENERAL_EDITOR:
//				return this.getAll();
//			default:
//				Set<Pitch> pitches = new HashSet<>();
//				for(GenreCommittee gc : person.getGenreCommittees()) {
//					Genre genre = gc.getGenre();
//					pitches.addAll(this.getAllPitchesWithGenre(genre));
//				}
//				return pitches;
//		}
	}

	@Override
	public Set<Pitch> getAllPitchesWithGenre(Genre genre) {
		return getDao().getAllPitchesWithGenre(genre);
	}
}
