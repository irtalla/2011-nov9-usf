package com.revature.data;

import java.util.Set;

import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.User;

public class UserJDBCPostgres implements UserDAO {

	@Override
	public User add(User u) throws NonUniqueUsernameException, NonUniqueEmailException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}

}
