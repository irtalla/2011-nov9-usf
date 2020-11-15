package com.revature.services;

public interface UserService {
	
	//create method: a User makes
	
	//while this is pretty bare-bones, it is the only thing a "User"
	//and only a user alone can do. "read" method.
	public boolean validatePotentialUser(String username, String password);
}
