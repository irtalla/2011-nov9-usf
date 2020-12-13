package com.revature.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.exceptions.InvalidCommitteeSizeException;

public class GenreCommitteeHibernate extends GenericHibernate<GenreCommittee> implements GenreCommitteeDAO{

	public GenreCommitteeHibernate() {
		super(GenreCommittee.class);
	}

	@Override
	public GenreCommittee addGenreCommittee(GenreCommittee gc) throws InvalidCommitteeSizeException {
		Set<Person> seniorEditors = gc.getSeniorEditors();
		if(gc.getMembers().size() >= 3 && seniorEditors.size() >= 1 && seniorEditors.size() < 3) {
			this.add(gc);
		}else {
			throw new InvalidCommitteeSizeException();
		}
		return gc;
	}

	@Override
	public GenreCommittee updateGenreCommittee(GenreCommittee gc) throws InvalidCommitteeSizeException {
		Set<Person> seniorEditors = gc.getSeniorEditors();
		if(gc.getMembers().size() >= 3 && seniorEditors.size() >= 1 && seniorEditors.size() < 3) {
			this.update(gc);
		}else {
			throw new InvalidCommitteeSizeException();
		}
		return gc;
	}

	@Override
	public GenreCommittee getByGenre(Genre g) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<GenreCommittee> criteria = cb.createQuery(GenreCommittee.class);
		Root<GenreCommittee> root = criteria.from(GenreCommittee.class);
		
		Predicate predicateForGenre = cb.equal(root.get("genre"), g.toString());
		
		criteria.select(root).where(predicateForGenre);
		
		GenreCommittee gc = s.createQuery(criteria).getSingleResult();
		return gc;
	}

}
