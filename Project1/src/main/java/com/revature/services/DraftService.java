package com.revature.services;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Person;
import com.revature.exceptions.DraftFromUnapprovedPitchException;

public interface DraftService extends GenericService<Draft>{

	Set<Draft> getDraftsViewableBy(Person p);
	//create:
//	public Draft addDraft(Draft p) throws DraftFromUnapprovedPitchException;
	
//	// read:
//	public Draft getDraftById(Integer id);
//	public Set<Draft> getAll();
//	
//	// update
//	public Draft updateDraft(Draft p);
//	
//	// delete
//	public void deleteDraft(Draft p);

//	//integration methods:
//	public Draft submitDraftForProofreading(Draft d); //this is really "adding" a draft
}
