package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	
	public UserServiceImpl() {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();
	}
	
	
	@Override
	public Integer addUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getByUsername(username);
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
