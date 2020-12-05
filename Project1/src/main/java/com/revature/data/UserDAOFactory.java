package com.revature.data;

public class UserDAOFactory {

	public UserDAO getUserDao() {
		return new UserHibernatePostgres();
	}

}
