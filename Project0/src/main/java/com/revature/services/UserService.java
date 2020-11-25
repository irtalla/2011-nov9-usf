package com.revature.services;

import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.User;

public interface UserService {
	public Integer addUser(User u) throws NonUniqueUsernameException;
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public Set<User> getUsers();
	public Set<User> getUsersByRole(String roleName);
	public void updateUser(User u);
	public void removeUser(User u);
}
