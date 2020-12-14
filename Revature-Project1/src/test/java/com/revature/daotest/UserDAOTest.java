package com.revature.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Author;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.services.UserServicesImpl;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {
	
	@Mock
	static UserDAO userDAO;
	
	@InjectMocks
	static UserServicesImpl userServ;
	
	@Test
	public void testValidLogin() {
		String username = "palimpsest";
		String password = "sortiedVesenrico";
		
		User user = new User();
		user.setId(1);
		user.setUsername(username);
		user.setPassword(password);
		
		Author author = new Author();
		author.setAuthorId(1);
		author.setUserInfo(user);
		author.setName("Julenpop Gasbeam");
		
		when(userDAO.verifyAUser(username, password)).thenReturn(author);		
		assertEquals(userDAO.verifyAUser(username, password), author);
		verify(userDAO).verifyAUser(username, password);
	}
}
