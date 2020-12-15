package com.revature.data;

public class RequestDAOFactory {
    public RequestDAO getRequestDAO() {
        return new RequestHibernate();
    }
}
