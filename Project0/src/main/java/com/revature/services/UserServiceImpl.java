package com.revature.services;

import java.util.Set;

import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	
	public UserServiceImpl() {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();
	}

	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException {
		return userDao.add(u).getId();
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public Set<User> getUsers() {
		return userDao.getAll();
	}

	@Override
	public Set<User> getUsersByRole(String roleName) {
		return userDao.getByRole(roleName);
	}

	@Override
	public void updateUser(User u) {
		userDao.update(u);
	}

	@Override
	public void removeUser(User u) {
		userDao.delete(u);
	}

}
