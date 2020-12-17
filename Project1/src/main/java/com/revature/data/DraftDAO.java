package com.revature.data;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.exceptions.DraftFromUnapprovedPitchException;

public interface DraftDAO extends GenericDAO<Draft>{
	Integer addDraft(Draft d) throws DraftFromUnapprovedPitchException;
	Set<Draft> getPendingDraftsWithGenre(Genre g);
}
