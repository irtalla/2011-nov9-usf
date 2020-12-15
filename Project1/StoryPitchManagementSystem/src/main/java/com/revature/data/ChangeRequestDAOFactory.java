package com.revature.data;

public class ChangeRequestDAOFactory {

	public ChangeRequestDAO getChangeRequestDAO() {        
		return new ChangeRequestPostgreSQL();
	}
}
