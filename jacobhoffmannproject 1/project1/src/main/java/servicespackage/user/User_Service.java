package servicespackage.user;

import exceptions.NonUniqueUsernameException;
import models.Users;

public interface User_Service {
	public Integer addUser(Users u) throws NonUniqueUsernameException;
	public Users getUserById(Integer id);
	public Users getUserByUsername(String u);
	public void updateUser(Users u);
}
