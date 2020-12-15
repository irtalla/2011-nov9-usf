package com.revature.data;

import com.revature.beans.Person;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PersonDAO extends GenericDAO<Person>{
	//different than generic add, to accommodate custom exception
	void addPerson(Person p) throws 
		NonUniqueUsernameException, 
		NonAuthorHasPitchesException; 
	void updatePerson(Person p) throws 
		NonUniqueUsernameException,
		NonAuthorHasPitchesException;
	Person getByUsername(String username);
}
