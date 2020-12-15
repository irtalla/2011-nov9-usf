package com.revature.services;

import com.revature.models.Committee;
import com.revature.models.Role;
import com.revature.models.User;

import java.util.Set;

import com.revature.exceptions.*;

public interface UserService {
	public Integer addUser(User u) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException;
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public User getUserByEmail(String email);
	public Set<User> getUserByRole(Role role);
	public Set<User> getAllUsers();
	public void updateUser(User u) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException;
	public void deleteUser(User u);
	public Set<Committee> getUserCommitees(User t);
}
