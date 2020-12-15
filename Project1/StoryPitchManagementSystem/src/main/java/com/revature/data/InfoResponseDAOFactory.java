package com.revature.data;

public class InfoResponseDAOFactory {

	public InfoResponseDAO getInfoResponseDAO() {        
		return new InfoResponsePostgreSQL();
	}
}
