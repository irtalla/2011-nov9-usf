package com.revature.data;

import com.revature.models.Role;
import com.revature.models.User;

import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;

public interface UserDAO extends GenericDAO<User> {
	public Integer add(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException;
	public User getByUsername(String username);
	public User getByEmail(String email);
	public Set<User> getByRole(Role role);
	public void update(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException;
}
