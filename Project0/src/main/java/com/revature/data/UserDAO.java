package com.revature.data;


import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {

	public User add(User u) throws NonUniqueUsernameException;
	public User getByUsername(String username);
}
