package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.exceptions.NonUniqueRoleException;
import com.revature.models.Role;

@TestMethodOrder(OrderAnnotation.class)
class RoleDAOTest {
	private static RoleDAO roleDao;
	private static Role sampleRole;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RoleDAOFactory rFactory = new RoleDAOFactory();
		roleDao = rFactory.getRoleDao();
		
		sampleRole = new Role();
		sampleRole.setId(5);
		sampleRole.setName("sample_role");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Role a = roleDao.add(sampleRole);
			assertNotEquals(a.getId(), 0);
			assertEquals(a.getName(), sampleRole.getName());
		} catch (NonUniqueRoleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Order(2)
	@Test
	void testGetById() {
		Role a = roleDao.getById(sampleRole.getId());
//		System.out.println(a);
//		System.out.println(sampleRole);
		assertEquals(a.getName(), sampleRole.getName());
		
	}
	
	@Order(3)
	@Test
	void testGetByName() {
		Role a = roleDao.getByName(sampleRole.getName());
//		System.out.println(a);
//		System.out.println(sampleRole);
		assertEquals(a.getName(), sampleRole.getName());
		
	}
	
	@Order(4)
	@Test
	void testGetAll() {
		Set<Role> roles = roleDao.getAll();
		assertTrue(roles.contains(sampleRole));
		
	}
	
	@Order(5)
	@Test
	void testUpdate() {
		Role a = roleDao.getById(sampleRole.getId());
		a.setName("updated_role");
		try {
			roleDao.update(a);	
		} catch (NonUniqueRoleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertNotEquals(sampleRole.getName(), roleDao.getById(a.getId()).getName());
		
	}
	
	@Order(6)
	@Test
	void testDelete() {
		roleDao.delete(sampleRole);
		Set<Role> roles = roleDao.getAll();
		assertFalse(roles.contains(sampleRole));
	}

}
