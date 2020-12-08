package JDBC;

public class EmployeeDAOFactory {
	
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeePost();
	}

}
