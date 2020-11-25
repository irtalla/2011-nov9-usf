package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.data.BicycleDAO;
import spencer.revature.data.BicycleDAOFactory;
import spencer.revature.data.CustomerDAO;
import spencer.revature.data.CustomerDAOFactory;
import spencer.revature.data.EmployeeDAO;
import spencer.revature.data.EmployeeDAOFactory;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDao;
	
	public EmployeeServiceImpl() {
		EmployeeDAOFactory employeeDaoFactory = new EmployeeDAOFactory();
		employeeDao = employeeDaoFactory.getEmployeeDAO();
		
	}
	
	@Override
	public Integer addEmployee(Employee e) {
		return employeeDao.add(e).getId();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeDao.getById(id);
	}

	@Override
	public Set<Employee> getEmployees() {
		return employeeDao.getAll();
	}

	@Override
	public void updateEmployee(Employee e) {
		employeeDao.update(e);
	}

	@Override
	public void removeEmployee(Employee e) {
		employeeDao.delete(e);
		
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return employeeDao.getByUsername(username);
	}

}
