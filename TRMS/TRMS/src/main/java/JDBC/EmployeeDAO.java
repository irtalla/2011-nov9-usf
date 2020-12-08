package JDBC;
import Entity.Employee;

public interface EmployeeDAO extends Generics<Employee> {
	
	public Employee getByUserName(String username);

}
