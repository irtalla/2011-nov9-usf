package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;

public interface CustomerService {
	// "create" method: returns the unique identifier of the added Bicycle
	public Integer addCustomer(Customer c);
	// "read" methods
	public Customer getCustomerById(Integer id);
	public Customer getCustomerByUsername(String username);
	public Set<Customer> getCustomers();
	// "update" methods
	public void updateCustomer(Customer c);
	// "delete" methods
	public void removeCustomer(Customer c);
	Set<Bicycle> getCustomerBikes(Customer c);
}