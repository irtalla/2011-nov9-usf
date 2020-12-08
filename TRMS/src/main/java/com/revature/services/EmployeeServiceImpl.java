package com.revature.services;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.data.DAOFactory;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Integer addEmployee(Employee e) {
		return DAOFactory.getEmployeeDAO().add(e).getId();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return DAOFactory.getEmployeeDAO().getById(id);
	}

	@Override
	public Employee getEmployeeByLogin(String username, String password) {
		// Plausible deniability is good
		Employee e = DAOFactory.getEmployeeDAO().getByUserName(username);
		if (e != null && e.getPassword().equals(password))
			return e;
		else
			return null;
	}

	@Override
	public Set<Employee> getEmployees() {
		return DAOFactory.getEmployeeDAO().getAll();
	}

	@Override
	public void updateEmployee(Employee e) {
		DAOFactory.getEmployeeDAO().update(e);

	}

	@Override
	public void removeEmployee(Employee e) {
		DAOFactory.getEmployeeDAO().delete(e);

	}

}
