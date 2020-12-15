package com.revature.services;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Person;

public interface DraftService {
	// create
	public Integer addDraft(Draft d, Person author);
	// read
	public Draft getDraftById(Integer id);
	public Set<Draft> getDrafts();
	public Set<Draft> getPendingDrafts();
	// update
	public void updateDraft(Draft d);
	public void approveDraft(Draft d, Person p);
	public void rejectDraft(Draft d);
	// delete
	public void removeDraft(Draft d);
}
