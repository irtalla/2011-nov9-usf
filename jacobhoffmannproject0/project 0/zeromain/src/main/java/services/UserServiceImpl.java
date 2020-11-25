package services;

import java.util.Set;

import data.UserDao;
import exceptions.NonUniqueUsernameException;
import models.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public UserServiceImpl() {
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		userDao = UserDaoFactory.getUserDAO();
	}
	// create
	public Integer addUser(User u) throws NonUniqueUsernameException  {
		return userDao.add(u).getUserId();
	}
	// read
	public User getUserById(Integer id) {
		return userDao.getById(id);
	}
	public User getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}
	// update
	public void updateUser(User u) {
		userDao.update(u);
	}
	// delete
	public void deleteUser(User u) {
		userDao.delete(u);
	}
	@Override
	public Set<User> getAll() {
		
		return userDao.getAll();
	}

}
