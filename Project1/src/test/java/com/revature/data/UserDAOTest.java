package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Role;
import com.revature.models.User;

class UserDAOTest {
	private static UserDAO userDao;
	private static User sampleUser;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		UserDAOFactory uFactory = new UserDAOFactory();
		userDao = uFactory.getUserDao();
		
		sampleUser = new User();
		sampleUser.setFirstName("Andrew");
		sampleUser.setLastName("Ryan");
		sampleUser.setEmail("andrew.ryan@revature.net");
		sampleUser.setUsername("amanchooses");
		sampleUser.setPassword("aslaveobeys");
		Role r = new Role();
		r.setId(4);
		r.setName("EditorIII");
		sampleUser.setRole(r);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
