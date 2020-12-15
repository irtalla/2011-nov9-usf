package servicespackage.employee;

import data.Employee_DAOFactory;
import data.Employee_Dao;
import data.User_DAOFactory;
import models.Employee;

public class Employee_ServiceImpl implements Employee_Service{
	private Employee_Dao empdao;
	public Employee_ServiceImpl() {
		Employee_DAOFactory employeedaofactory = new Employee_DAOFactory();
		empdao = employeedaofactory.getEmployeeDao();
	}
	public Employee getByEmployeeId(Integer i) {
		// TODO Auto-generated method stub
		return empdao.getEmployeeById(i);
	}

	public Employee getByUserId(Integer i) {
		// TODO Auto-generated method stub
		return empdao.getByUserId(i);
	}
	public Integer addEmployee(Employee e) {
		// TODO Auto-generated method stub
		return empdao.add(e).getId();
	}
	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		empdao.updateEmployee(e);
	}

}
