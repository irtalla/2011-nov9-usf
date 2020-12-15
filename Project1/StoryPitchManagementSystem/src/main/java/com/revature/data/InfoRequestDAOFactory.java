package com.revature.data;

public class InfoRequestDAOFactory {

	public InfoRequestDAO getInfoRequestDAO() {        
		return new InfoRequestPostgreSQL();
	}
}
