package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Role;
import com.revature.models.User;

@TestMethodOrder(OrderAnnotation.class)
class UserDAOTest {
	private static UserDAO userDao;
	private static User sampleUser;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		UserDAOFactory uFactory = new UserDAOFactory();
		userDao = uFactory.getUserDao();
		
		sampleUser = new User();
		sampleUser.setId(1);
		sampleUser.setFirstName("Andrew");
		sampleUser.setLastName("Ryan");
		sampleUser.setEmail("andrew.ryan@revature.net");
		sampleUser.setUsername("aManChooses");
		sampleUser.setPassword("aSlaveObeys");
		Role r = new Role();
		r.setId(4);
		r.setName("EditorIII");
		sampleUser.setRole(r);
		
	}

	@Order(1)
	@Test
	void testAddUser() {
		try {
			Integer newId = userDao.add(sampleUser);
			sampleUser.setId(0);
			assertNotEquals(newId, 0);
			sampleUser.setId(newId);
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		} catch (NonUniqueEmailException e) {
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Order(2)
	@Test
	void testDuplicateUser() {
		assertThrows(NonUniqueEmailException.class, () -> {
			userDao.add(sampleUser);
		});
	}
	
	@Order(3)
	@Test
	void testGetUserById() {
		System.out.println("Testing id");
		User a = userDao.getById(sampleUser.getId());
//		System.out.println("sample user: " + sampleUser);
//		System.out.println("returned user: " + a);
		assertTrue(a.equals(sampleUser));
		
	}
	
	@Order(4)
	@Test
	void testGetUserByUsername() {
		System.out.println("Testing username");
		User a = userDao.getByUsername(sampleUser.getUsername());
//		System.out.println("sample user:" + sampleUser));
//		System.out.println("returned user:" + a);
		assertTrue(a.equals(sampleUser));
		
	}

	@Order(5)
	@Test
	void testGetUserByEmail() {
		System.out.println("Testing email");
		User a = userDao.getByEmail(sampleUser.getEmail());
//		System.out.println("sample user:" + sampleUser);
//		System.out.println("returned user:" + a);
		assertTrue(a.equals(sampleUser));
		
	}
	
	@Order(6)
	@Test
	void testGetByRole() {
		System.out.println("Testing role");
		Set<User> a = userDao.getByRole(sampleUser.getRole());
		assertTrue(a.contains(sampleUser));
		
	}
	
	@Order(7)
	@Test
	void testGetAll() {
		System.out.println("Testing all");
		Set<User> a = userDao.getAll();
		assertTrue(a.contains(sampleUser));
		
	}
	
	@Order(8)
	@Test
	void testUpdate() {
		System.out.println("Testing update");
		User a = sampleUser;
		a.setEmail("a.r.@rev.net");
		a.setPassword("newPassword");
		Role role = new Role();
		role.setId(1);
		role.setName("Author");
		a.setRole(role);
		try {
			userDao.update(sampleUser);
		}catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		} catch (NonUniqueEmailException e) {
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		User b = userDao.getById(a.getId());
		assertTrue(b.getEmail().equals("a.r.@rev.net"));
		assertTrue(b.getPassword().equals("newPassword"));
		assertTrue(b.getRole().getId().equals(1));
		assertTrue(b.getRole().getName().equals("Author"));
		
	}
	
	@Order(9)
	@Test
	void testDelete() {
		System.out.println("Testing delete");
		userDao.delete(sampleUser);
		
		assertFalse(userDao.getAll().contains(sampleUser));
	}
	
}
