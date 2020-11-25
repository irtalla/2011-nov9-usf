package com.revature.data;

import java.util.Set;
import com.revature.beans.User;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u);
	public Set<User> getAll();
	public User getByUsername(String username);
	public User getById(Integer id);
}
