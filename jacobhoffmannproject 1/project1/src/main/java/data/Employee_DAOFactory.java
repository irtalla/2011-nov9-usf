package data;

import models.Employee;

public class Employee_DAOFactory {
	public Employee_Dao getEmployeeDao() {
		return new Employee_Hibernate();
	}
}
