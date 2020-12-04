package com.revature.data;

public class RoleDAOFactory {

	public RoleDAO getRoleDao() {
		return new RoleJDBCPostgres();
	}

}
