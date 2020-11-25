package spencer.revature.data;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {
	public Customer getByUsername(String username);

	public Set<Bicycle> getCustomersBikes(Customer c);
}
