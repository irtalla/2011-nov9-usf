package com.revature.services;

import java.util.Set;

import com.revature.beans.Department;
import com.revature.data.DAOFactory;

public class DepartmentServiceImpl implements DepartmentService {

	
	@Override
	public Department getDepartmentById(Integer id) {
		return DAOFactory.getDepartmentDAO().getById(id);
	}

	@Override
	public Set<Department> getDepartments() {
		return DAOFactory.getDepartmentDAO().getAll();
	}

}
