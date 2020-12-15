package servicespackage.employee;

import models.Employee;

public interface Employee_Service {
		public Integer addEmployee(Employee e);
		public Employee getByEmployeeId(Integer i);
		public Employee getByUserId(Integer i);
		public void updateEmployee(Employee e);
		
}
