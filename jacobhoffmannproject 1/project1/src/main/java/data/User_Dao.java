package data;

import exceptions.NonUniqueUsernameException;
import models.Users;

public interface User_Dao {
	public Users add(Users U) throws NonUniqueUsernameException;
	public Users getByUsername(String username);
	public Users getUserById(Integer id);
	public void updateUser(Users u);
}
