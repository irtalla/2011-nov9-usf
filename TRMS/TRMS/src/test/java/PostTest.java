import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Entity.Employee;
import Entity.Role;
import JDBC.EmployeePost;

public class PostTest {
	
	@Test
	public static void getByIdTest() {
		EmployeePost ep = new EmployeePost();
		Employee emp = new Employee();
		
		emp = ep.getById(2);
		System.out.println(emp.getFullName());
	}

}
