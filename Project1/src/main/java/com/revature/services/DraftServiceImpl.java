package com.revature.services;

import com.revature.beans.Draft;
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
	public Draft add(Draft d) {
		try {
			return getDao().addDraft(d); //rather than using draftDao.add(d)
		} catch (DraftFromUnapprovedPitchException e) {
			e.printStackTrace();
		}
		return null;
	}
}
