package com.revature.services;

import java.util.Set;

import com.revature.beans.Employee;

public interface EmployeeService {

	public Integer addEmployee(Employee e);
	// "read" methods
	public Employee getEmployeeById(Integer id);
	public Employee getEmployeeByLogin(String username, String password);
	public Set<Employee> getEmployees();
	// "update" methods
	public void updateEmployee(Employee e);
	// "delete" methods
	public void removeEmployee(Employee e);
}
