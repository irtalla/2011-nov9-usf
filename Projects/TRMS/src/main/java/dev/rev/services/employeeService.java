package dev.rev.services;


import dev.rev.beans.employee;
import dev.rev.exception.NonUniqueUsernameException;

public interface employeeService {
	
	// create
	public Integer addPerson(employee p) throws NonUniqueUsernameException;
	// read
	public employee getPersonById(Integer id);
	public employee getPersonByUsername(String username);
	public void updateemployee(employee emp);

}
