package Service;


import Entity.Employee;
import JDBC.EmployeeDAO;
import JDBC.EmployeeDAOFactory;

public class EmployeeServImp implements EmployeeServ {
	private EmployeeDAO employeeDao;
	
	public EmployeeServImp() {
		EmployeeDAOFactory employeeDaoFactory = new EmployeeDAOFactory();
		employeeDao = employeeDaoFactory.getEmployeeDAO();
	}
	@Override
	public Employee getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.getById(id);
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		return employeeDao.getByUserName(username);
	}

	@Override
	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		employeeDao.update(e);
	}
	
	

}
