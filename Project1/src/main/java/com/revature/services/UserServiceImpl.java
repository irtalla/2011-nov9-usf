package com.revature.services;

import java.util.Set;

import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;
import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Role;
import com.revature.models.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao;

	public UserServiceImpl() {
		UserDAOFactory userFactory = new UserDAOFactory();
		userDao = userFactory.getUserDao();
	}

	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		return userDao.add(u);
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
	public User getUserByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public Set<User> getUserByRole(Role role) {
		return userDao.getByRole(role);
	}

	@Override
	public Set<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void updateUser(User u) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		userDao.update(u);
	}

	@Override
	public void deleteUser(User u) {
		userDao.delete(u);
	}

}
