package com.trms.data;

public class RequestDAOFactory {
	public RequestDAO getRequestDAO() {
		return new RequestHibernate();
	}
}
