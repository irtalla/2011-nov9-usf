package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.UserDAO;
import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Role;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	static UserDAO userDao;
	
	@InjectMocks
	static UserServiceImpl userServ;
	
	static Set<User> usersMock = new HashSet<>();
	static Integer userSeqMock = 1;
	
	@Test
	public void testAddUser() {
		User u = new User();
		u = new User();
		u.setId(1);
		u.setFirstName("Andrew");
		u.setLastName("Ryan");
		u.setEmail("andrew.ryan@revature.net");
		u.setUsername("aManChooses");
		u.setPassword("aSlaveObeys");
		Role r = new Role();
		r.setId(4);
		r.setName("EditorIII");
		u.setRole(r);

		usersMock.add(u);
		User u2 = u;
		u2.setId(userSeqMock++);
		
		try {
			when(userDao.add(u)).thenReturn(u2.getId());
			System.out.println(u.getId());
			assertEquals(u.getId().intValue(), userServ.addUser(u).intValue());
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
		} catch (NonUniqueEmailException e) {
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			verify(userDao).add(u);
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
}
