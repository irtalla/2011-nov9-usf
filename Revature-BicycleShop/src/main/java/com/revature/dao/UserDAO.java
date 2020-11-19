package com.revature.dao;

import java.util.Set;

import com.revature.beans.User;
import com.revature.beans.Employee;
import com.revature.beans.Offer;
import com.revature.beans.Customer;

public interface UserDAO {
	public Set<User> getAllUsers();
	public Set<Employee> getAllEmployees();
	public Set<Customer> getAllCustomers();
	
	public void registerACustomer(String username, String password);
	
	public void calculatePayment(Offer offer);
}
