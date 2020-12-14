package com.revature.services;

import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserHibernate;

public class UserServicesImpl implements UserFunctions {
	private UserDAO userDAO;
	
	public UserServicesImpl() {
		userDAO = new UserHibernate();
	}

	@Override
	public Object retrieveAUser(String username, String password) {
		return userDAO.verifyAUser(username, password);
	}

	@Override
	public int registerAUser(String username, String password) {
		// TODO Auto-generated method stub
		userDAO.registerAUser(username, password);
		return 0;
	}

	@Override
	public boolean deleteAUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
