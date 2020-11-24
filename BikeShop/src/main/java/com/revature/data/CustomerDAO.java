package com.revature.data;

import com.revature.beans.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {
	public Customer getByLoginCredentials(String username, String password);
	public Boolean verifyUniqueUsername(String username);
}
