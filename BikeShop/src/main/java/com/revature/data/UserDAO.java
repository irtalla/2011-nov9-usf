package com.revature.data;

import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u) throws NonUniqueUsernameException;
	public User getByUsername(String username);
	public Set<Bike> getBikesByUserId(Integer id) throws SQLException;
}
