package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Entity.Employee;
import Service.EmployeeServImp;

public class employeeServImpTest {
	static Employee emp;
	static EmployeeServImp esi = new EmployeeServImp();
	static int x =5;
	
	@Test
	public static void getByIdTest() {
		emp = esi.getEmployeeById(x);
		
		assertEquals("jamaal fisher",emp.getFullName());
	}
	
	@Test
	public static void updateTest() {
		emp.setAvailFunds(30);
		esi.updateEmployee(emp);
		
		assertEquals(30,esi.getEmployeeByUsername("jakeem").getAvailFunds());
	}
	
	@Test
	public static void getByUserNameTest() {
		emp = esi.getEmployeeByUsername("dep");
		
		assertEquals(3,emp.getId());
	}

}
