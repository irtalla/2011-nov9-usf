package com.revature.services;

import com.revature.beans.User;

public interface UserFunctions {
	//public User retrieveAUser(String username, String password);
	public Object retrieveAUser(String username, String password);
	public int registerAUser(String username, String password);
	public boolean deleteAUser(User user);
}
