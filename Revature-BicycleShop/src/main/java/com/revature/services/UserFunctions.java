package com.revature.services;

import java.util.Set;

import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOFactory;

public class UserFunctions implements UserService {
	
	private UserDAO userDAO;
	
	public UserFunctions() {
		UserDAOFactory udf = new UserDAOFactory();
		userDAO = udf.getUserDAO();
	}
	
	//note: while this does look rather weird, it is noted that
	//effectively a User is a CUstomer or an Employee.
	//This effectively does cut down on what nees to be done
	//although it is super-inefficient even as is.
	public boolean validatePotentialUser(String username, String password) {
		Set<User> allUsers = userDAO.getAllUsers();
		Customer supposedUserC = new Customer(username, password);
		Employee supposedUserE = new Employee(username, password);
		
		
		if (allUsers.contains(supposedUserC) || allUsers.contains(supposedUserE)) {
			return true;
		}
		
		return false;
	}
}
