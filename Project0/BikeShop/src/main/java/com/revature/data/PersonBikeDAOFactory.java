package com.revature.data;

public class PersonBikeDAOFactory {
	public PersonBikeDAO getPersonBikeDAO(){
		
		return new PersonBikePostgres();	
		
	}
}