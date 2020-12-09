import org.junit.jupiter.api.Test;

import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;



public class testUserHibernate {
	
	@Test
	public void testUser() {
		
		UserDAOFactory test = new UserDAOFactory();
		UserDAO testingit = test.getUserDAO();
		testingit.getByUsername("test");
		
		
	}
}
