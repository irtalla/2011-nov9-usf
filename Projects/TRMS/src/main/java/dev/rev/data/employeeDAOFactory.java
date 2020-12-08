package dev.rev.data;

public class employeeDAOFactory {

	public employeeDAO getemployeeDAO() {
		return new employeehiber();
	}
}
