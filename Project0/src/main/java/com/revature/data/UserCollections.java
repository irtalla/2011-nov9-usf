package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.User;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class UserCollections implements UserDAO {
	
private Set<User> users;
	
	public UserCollections() {
		users = new HashSet<>();
		
		User u = new User();
		u.setId(1);
		u.setUsername("sierra");
		u.setPassword("pass");
		Role r = new Role();
		r.setId(1);
		r.setName("Employee");
		u.setRole(r);
		users.add(u);
	}

	@Override
	public User add(User t) throws NonUniqueUsernameException {
		for (User u : users) {
			if (u.getUsername().equals(t.getUsername())) {
				throw new NonUniqueUsernameException();
			}
		}
		t.setId(users.size()+1);
		users.add(t);
		return t;
	}

	@Override
	public User getById(Integer id) {
		for (User u: users) {
			if (u.getId().equals(id))
				return u;
		}
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
		for (User u: users) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	@Override
	public Set<User> getAll() {
		return users;
	}

	@Override
	public void update(User t) {
		for (User u: users) {
			if (u.getId() == t.getId()) {
				this.delete(u);
				users.add(t);
				return;
			}
		}
		//return;
	}

	@Override
	public void delete(User t) {
		users.remove(t);
	}

}
