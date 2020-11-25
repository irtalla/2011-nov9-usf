package com.revature.services;

import com.revature.beans.Person;
import com.revature.exception.NonUniqueUsernameException;

public interface PersonServices {
	Person add(Person t) throws NonUniqueUsernameException;

	// create
		public Person createUser(Person p) throws NonUniqueUsernameException;
		public Person loginUser(String userId);

}
