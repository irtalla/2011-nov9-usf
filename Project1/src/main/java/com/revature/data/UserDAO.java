package com.revature.data;

import com.revature.models.User;

import com.revature.exceptions.*;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u) throws NonUniqueUsernameException, NonUniqueEmailException;
	public User getByUsername(String username);
}
