package com.revature.data;

public class StorytypeDAOFactory {
	public StorytypeDAO getStorytypeDAO() {
        
        return new StorytypeHibernate();
    }
}