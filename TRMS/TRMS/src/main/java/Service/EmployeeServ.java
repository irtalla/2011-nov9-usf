package Service;

import Entity.Employee;

public interface EmployeeServ {
	
	public Employee getEmployeeById(Integer id);
	public Employee getEmployeeByUsername(String username);
	public void updateEmployee(Employee e);

}
