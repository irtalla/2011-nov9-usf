package com.revature.data;

import com.revature.beans.Pitch;

public class PitchDAOFactory implements GenericDAOFactory<Pitch>{
	@Override
	public PitchDAO getDAO() {
		return new PitchHibernate();
	}
}
