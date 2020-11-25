package com.revature.services;

import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserService {
	
	public Integer addUser(User u) throws NonUniqueUsernameException;
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public Set<User> getUsers();
	public void updateUser(User u);
	public void removeUser(User u);
	public Set<Bike> getBikesByUserId(Integer id) throws SQLException;
}
