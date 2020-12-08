import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entity.Employee;
import JDBC.EmployeePost;

class PostGTest {

	@Test
	public static void getByIdTest() {
			EmployeePost ep = new EmployeePost();
			Employee emp = new Employee();
			
			emp = ep.getById(2);
			int x = emp.getRole().getId();
			assertEquals(3,x);
	}

}
