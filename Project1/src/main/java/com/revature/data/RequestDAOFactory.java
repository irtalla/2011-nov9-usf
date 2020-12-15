package com.revature.data;

public class RequestDAOFactory {
	public RequestDAO getRequestDao() {
		return new RequestHibernatePostgres();
	}
}
