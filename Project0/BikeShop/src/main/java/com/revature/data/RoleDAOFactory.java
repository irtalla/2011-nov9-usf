package com.revature.data;

public class RoleDAOFactory {
	
	public RoleDAO getRoleDAO() {
		
		return new RolePostgres();	
	}
}
