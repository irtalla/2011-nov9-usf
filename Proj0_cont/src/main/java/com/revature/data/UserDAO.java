package com.revature.data;

import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.User;
import com.revature.models.Inventory;

public interface UserDAO extends GenericDAO<User> {
	public User add(User t) throws NonUniqueUsernameException;
	public User getByUsername(String username);
	public Set<User> getByRole(String roleName);
}
