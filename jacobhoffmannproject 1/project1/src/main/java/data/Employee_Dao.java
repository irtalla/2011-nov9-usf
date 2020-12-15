package data;

import models.Employee;

public interface Employee_Dao {
	public Employee getEmployeeById(Integer id);
	public Employee getByUserId( Integer i);
	public Employee add(Employee e);
	public void updateEmployee(Employee e);
}
