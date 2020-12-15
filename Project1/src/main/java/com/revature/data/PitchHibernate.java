package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.SurplusPitchException;

public class PitchHibernate extends GenericHibernate<Pitch> implements PitchDAO{

	public PitchHibernate() {
		super(Pitch.class);
	}

	@Override
	public Pitch addPitch(Pitch p) throws SurplusPitchException, NonAuthorHasPitchesException {
		Person originator = p.getAuthor();
		if(!originator.getRole().equals(Role.AUTHOR) && !p.getStatus().equals(Status.SAVED)) {
			throw new NonAuthorHasPitchesException();
		}else if(originator.getTotalStoryPoints() >= 100){
			throw new SurplusPitchException();
		}else {
			this.add(p);
		}
		return p;
	}

	@Override
	public Pitch updatePitch(Pitch p) throws SurplusPitchException {
		//check that updating the status of a previously pending pitch
		//only occurs if the author has total story points less than 100
		Person originator = p.getAuthor();
		if(originator.getTotalStoryPoints() >= 100 && !p.getStatus().equals(Status.SAVED)){
			throw new SurplusPitchException();
		}else {
			this.add(p);
		}
		return p;
	}

	@Override
	public Set<Pitch> getAllPitchesWithGenre(Genre g) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Pitch> cq = cb.createQuery(Pitch.class);
		
		Root<Pitch> root = cq.from(Pitch.class);
		
		Predicate predicateForGenre = cb.equal(root.get("genre"), g.toString());
		cq.select(root).where(predicateForGenre);
		
		List<Pitch> matches = s.createQuery(cq).getResultList();
		return new HashSet<>(matches);
	}

	public Set<Pitch> getPitchesViewableBy(Person person) {
		Role role = person.getRole();
		switch(role) {
			case AUTHOR:
				System.out.println(person.getPitches().size());
				return person.getPitches(); //authored pitches
			case GENERAL_EDITOR:
				return this.getAll();
			default:
				Set<Pitch> pitches = new HashSet<>();
				for(GenreCommittee gc : person.getGenreCommittees()) {
					Genre genre = gc.getGenre();
					pitches.addAll(this.getAllPitchesWithGenre(genre));
				}
				return pitches;
		}
	}
}
