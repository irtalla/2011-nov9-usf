package dev.rev.data;

import dev.rev.beans.employee;

public interface employeeDAO extends genericDAO<employee> {
	
	public employee getbyusername(String name);
}
