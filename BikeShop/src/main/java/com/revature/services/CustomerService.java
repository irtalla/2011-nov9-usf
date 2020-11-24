package com.revature.services;

import java.util.Set;

import com.revature.beans.Customer;



public interface CustomerService {
	public Integer addCustomer(Customer c);
	// "read" methods
	public Customer getCustomerById(Integer id);
	public Customer getCustomerByLogin(String username, String password);
	public Boolean verifyUniqueUsername(String username);
	public Set<Customer> getCustomers();
	// "update" methods
	public void updateCustomer(Customer c);
	// "delete" methods
	public void removeCustomer(Customer c);

}
