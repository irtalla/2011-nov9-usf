package com.revature.services;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.exceptions.DraftFromUnapprovedPitchException;

public class DraftServiceImpl implements DraftService {
	private DraftDAO draftDao;
	
	public DraftServiceImpl() {
		DraftDAOFactory factory = new DraftDAOFactory();
		draftDao = factory.getDraftDao();
	}
	
	@Override
	public Draft addDraft(Draft p) throws DraftFromUnapprovedPitchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Draft getDraftById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Draft> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Draft updateDraft(Draft p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDraft(Draft p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Draft submitDraftForProofreading(Draft d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Draft approveDraft(Draft d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Draft denyDraft(Draft d) {
		// TODO Auto-generated method stub
		return null;
	}

}
