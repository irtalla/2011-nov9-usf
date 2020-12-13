package com.revature.services;

import com.revature.beans.Person;

public interface PersonService extends GenericService<Person>{
	Person getByUsername(String username);
	//integration methods:
//	public Draft submitDraftForProofreading(Person author, Draft draft);
}
