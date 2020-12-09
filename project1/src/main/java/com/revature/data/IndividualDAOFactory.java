package com.revature.data;

public class IndividualDAOFactory {
	public IndividualDAO getIndividualDAO() {
		return new IndividualHibernate();
	}
}
