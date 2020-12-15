package com.revature.services;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Status;
import com.revature.beans.Person;
import com.revature.data.DraftDAO;
import com.revature.data.DraftDAOFactory;
import java.util.HashSet;

public class DraftServiceImpl implements DraftService {
	private DraftDAO draftDAO;

	public DraftServiceImpl(){
		DraftDAOFactory draftDAOFactory = new DraftDAOFactory();
		draftDAO = draftDAOFactory.getDraftDAO();
	}

	public Integer addDraft(Draft d, Person author) {
		d.setAuthor(author);
		return draftDAO.add(d).getId();
	}

	public Draft getDraftById(Integer id) {
		return draftDAO.getById(id);
	}

	public Set<Draft> getDrafts() {
		return draftDAO.getAll();
	}

	public Set<Draft> getPendingDrafts() {
		return draftDAO.getPending();
	}

	public void updateDraft(Draft d) {
		draftDAO.update(d);
	}

	public void approveDraft(Draft d, Person p) {
		Set<Person> approvals = new HashSet<>();
		approvals.add(p);
		d.setApprovals(approvals);
		draftDAO.update(d);
	}

	public void rejectDraft(Draft d) {
		Status s = new Status();
		s.setName("Rejected");
		s.setId(3);
		d.setStatus(s);
		draftDAO.update(d);
	}

	public void removeDraft(Draft d) {
		draftDAO.delete(d);
	}

}
