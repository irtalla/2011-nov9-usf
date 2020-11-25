package com.revature.services;

import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

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
	public void updateUser(User u) {
		userDao.update(u);
	}
	@Override
	public void removeUser(User u) {
		userDao.delete(u);
	}
	@Override
	public Set<Bike> getBikesByUserId(Integer id) throws SQLException {
		return userDao.getBikesByUserId(id);
	}
}
