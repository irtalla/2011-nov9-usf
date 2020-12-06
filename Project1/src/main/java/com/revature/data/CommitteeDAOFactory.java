package com.revature.data;

public class CommitteeDAOFactory {

	public CommitteeDAO getCommitteeDao() {
		return new CommitteeHibernatePostgres();
	}
}
