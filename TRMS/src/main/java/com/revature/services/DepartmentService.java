package com.revature.services;

import java.util.Set;

import com.revature.beans.Department;

public interface DepartmentService {
	//public Integer addDepartment(Department e);
	// "read" methods
	public Department getDepartmentById(Integer id);
	public Set<Department> getDepartments();
	// "update" methods
	//public void updateDepartment(Department e);
	// "delete" methods
	//public void removeDepartment(Department e);
}
