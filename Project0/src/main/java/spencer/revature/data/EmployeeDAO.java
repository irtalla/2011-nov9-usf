package spencer.revature.data;

import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {
	public Employee getByUsername(String username);
}
