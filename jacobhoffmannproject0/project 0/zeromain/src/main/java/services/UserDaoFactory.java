package services;


import data.UserDao;
import data.UserPostgres;

public class UserDaoFactory {
	 public UserDao getUserDAO() {
	        
	        return new UserPostgres();
	    }
}
