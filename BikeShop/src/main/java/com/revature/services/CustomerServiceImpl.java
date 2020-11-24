package com.revature.services;

import java.util.Set;

import com.revature.beans.Customer;
import com.revature.data.CustomerDAO;
import com.revature.data.DAOFactory;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO dao = DAOFactory.getCustomerDAO();
	@Override
	public Integer addCustomer(Customer c) {
		return dao.add(c).getID();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<Customer> getCustomers() {
		return dao.getAll();
	}

	@Override
	public void updateCustomer(Customer c) {
		dao.update(c);

	}

	@Override
	public void removeCustomer(Customer c) {
		dao.delete(c);

	}

	@Override
	public Customer getCustomerByLogin(String username, String password) {
		return dao.getByLoginCredentials(username, password);
	}

	@Override
	public Boolean verifyUniqueUsername(String username) {
		
		return dao.verifyUniqueUsername(username);
	}

}
