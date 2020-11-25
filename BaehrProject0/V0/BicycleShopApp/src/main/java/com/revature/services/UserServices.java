package com.revature.services;

import com.revature.beans.User;

public interface UserServices {
	// create
	public Integer addUser(User u);
	// read
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	// update
	public void updateUser(User u);
	// delete
	public void deleteUser(User u);

}
