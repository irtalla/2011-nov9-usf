package com.revature.dao;

import com.revature.beans.User;

public interface UserDAO {
	public int registerAUser(String username, String password);
	public boolean removeAUser(User u);
	public Object verifyAUser(String username, String password);
}
