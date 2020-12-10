package com.revature.data;

public class AdditionalFileDAOFactory {
	public AdditionalFileDAO getAdditionalFileDao() {
		return new AdditionalFileHibernatePostgres();
	}
}
