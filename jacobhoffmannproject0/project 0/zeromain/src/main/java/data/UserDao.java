package data;

import exceptions.NonUniqueUsernameException;
import models.User;

public interface UserDao  extends GenericDAO<User>{
	public User add(User u)throws NonUniqueUsernameException;//add exception for non unique user name
	public User getByUsername(String username);
}
