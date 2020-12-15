package com.revature.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
import com.revature.data.DraftDAO;
import com.revature.data.DraftDAOFactory;
import com.revature.exceptions.DraftFromUnapprovedPitchException;

public class DraftServiceImpl extends GenericServiceImpl<Draft> implements DraftService {
	public DraftServiceImpl() {
		super(new DraftDAOFactory());
	}

	@Override
	public DraftDAO getDao() {
		return (DraftDAO) dao;
	}
	
	@Override
	public Integer add(Draft d) {
		try {
			return getDao().addDraft(d); //rather than using draftDao.add(d)
		} catch (DraftFromUnapprovedPitchException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Draft> getDraftsViewableBy(Person p) {
		Role role = p.getRole();
		switch(role) {
			case AUTHOR:
				return p.getDrafts(); //authored pitches
			case GENERAL_EDITOR:
				return p.getDraftsWhosePitchesIHaveReactedTo(); //general editors
			default:
				Set<Draft> drafts = new HashSet<>();
				for(GenreCommittee gc : p.getGenreCommittees()) {
					Genre genre = gc.getGenre();
					drafts.addAll(getAllDraftsWithGenre(genre));
				}
				return drafts;
		}
	}

	private Set<Draft> getAllDraftsWithGenre(Genre genre) {
		Set<Draft> drafts = new HashSet<>();
		for(Pitch p : new PitchServiceImpl().getAllPitchesWithGenre(genre)) {
			Draft d = p.getDraft();
			if(d != null) {
				drafts.add(d);
			}
		}
		return drafts;
	}

	@Override
	public Draft getByIdEagerly(Integer id) {
		return getDao().getByIdEagerly(id);
	}
}
