package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;

class EmployeePostgresTest {

	/*@Test
	void BasicAdd() {
		EmployeeDAO empDao;
		EmployeeDAOFactory empDaoFactory = new EmployeeDAOFactory();
		empDao = empDaoFactory.getEmployeeDAO();
		Employee e = new Employee();
		e.setUsername("SpencerCamp");
		e.setPassword("THEpassword");
		empDao.add(e);
	}*/
	
	@Test
	void getUser(){
		EmployeeDAO empDao;
		EmployeeDAOFactory empDaoFactory = new EmployeeDAOFactory();
		empDao = empDaoFactory.getEmployeeDAO();
		Employee e = empDao.getByUsername("EmpStore");
		
		assertEquals("THEpassword", e.getPassword());
	}
	
	@Test
	void getNoUser(){
		EmployeeDAO empDao;
		EmployeeDAOFactory empDaoFactory = new EmployeeDAOFactory();
		empDao = empDaoFactory.getEmployeeDAO();
		Employee e = empDao.getByUsername("Storsse");
		
		assertEquals(e, null);
	}

	@Test
	void getId(){
		EmployeeDAO empDao;
		EmployeeDAOFactory empDaoFactory = new EmployeeDAOFactory();
		empDao = empDaoFactory.getEmployeeDAO();
		Employee e = empDao.getById(2);
		
		assertEquals("SpencerCamp", e.getUsername());
	}
	
	@Test
	void getAll(){
		EmployeeDAO empDao;
		EmployeeDAOFactory empDaoFactory = new EmployeeDAOFactory();
		empDao = empDaoFactory.getEmployeeDAO();
		
		Set<Employee> emps = empDao.getAll();
		assertTrue(emps.size()>0);
		//assertTrue(emps.size()==3);
	}
}
