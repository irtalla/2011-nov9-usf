package com.revature.services;

import com.revature.beans.User;

public interface UserService {

	//create
	public Integer addUser(User u);
	//read
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	
	//update
	public User updateUser(User u);
	
	//delete
	public User deleteUser(User u);
}
