package com.revature.data;

import com.revature.models.Role;
import com.revature.models.User;

import java.util.Set;

import com.revature.exceptions.*;

public interface UserDAO extends GenericDAO<User> {
	public User add(User t) throws NonUniqueUsernameException, NonUniqueEmailException;
	public User getByUsername(String username);
	public User getByEmail(String email);
	public Set<User> getByRole(Role role);
}
