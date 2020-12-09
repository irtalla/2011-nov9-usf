package com.revature.data;

public class PitchDAOFactory {

	public PitchDAO getPitchDao() {
		return new PitchHibernatePostgres();
	}
}
