package com.revature.data;

public class PitchStageDAOFactory {
	public PitchStageDAO getPitchStageDao() {
		return new PitchStageHibernatePostgres();
	}
}
