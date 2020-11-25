package services;

import java.util.Set;

import exceptions.NonUniqueUsernameException;
import models.User;

public interface UserService {
	// create
	public Integer addUser(User u) throws NonUniqueUsernameException;
	// read
	public Set<User> getAll();
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	// update
	public void updateUser(User u);
	// delete
	public void deleteUser(User u);
}
