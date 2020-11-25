package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;

public class UserServicesImpl implements UserServices{
	
	private UserDAO userDao;
	
	public UserServicesImpl() {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		//System.out.println(userDao == null);
	}

	@Override
	public Integer addUser(User u) {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		userDao.add(u);
		return u.getUserID();
	}

	@Override
	public User getUserById(Integer id) {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		//System.out.println(userDao == null);
		return userDao.getById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		return userDao.getByUsername(username);
	}

	@Override
	public void updateUser(User u) {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		userDao.update(u);
		
	}

	@Override
	public void deleteUser(User u) {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		UserDAO userDao = userDaoFactory.getUserDAO();
		userDao.delete(u);
		
	}

}
