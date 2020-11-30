package com.revature.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	@Mock
	static PersonDAO personDao;
	
	@InjectMocks
	static PersonServiceImpl personServ;
	
	@Test
	public void testAddPerson() throws NonUniqueUsernameException {

	}
}
