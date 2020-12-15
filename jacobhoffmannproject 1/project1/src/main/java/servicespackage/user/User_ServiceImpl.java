package servicespackage.user;

import data.User_DAOFactory;
import data.User_Dao;
import exceptions.NonUniqueUsernameException;
import models.Users;

public class User_ServiceImpl implements User_Service {
	private User_Dao userdao;
	
	public User_ServiceImpl() {
		User_DAOFactory userdaofactory = new User_DAOFactory();
		userdao = userdaofactory.getUserDao();
	}

	public Integer addUser(Users u) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return userdao.add(u).getId();
	}

	public Users getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userdao.getUserById(id);
	}

	public Users getUserByUsername(String u) {
		// TODO Auto-generated method stub
		return userdao.getByUsername(u);
	}

	public void updateUser(Users u) {
		// TODO Auto-generated method stub
		userdao.updateUser(u);
	}
	
}
