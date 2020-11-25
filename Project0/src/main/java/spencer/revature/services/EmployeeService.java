package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;

public interface EmployeeService {
	// "create" method: returns the unique identifier of the added Bicycle
	public Integer addEmployee(Employee e);
	// "read" methods
	public Employee getEmployeeById(Integer id);
	public Employee getEmployeeByUsername(String username);
	public Set<Employee> getEmployees();
	// "update" methods
	public void updateEmployee(Employee e);
	// "delete" methods
	public void removeEmployee(Employee e);
}
