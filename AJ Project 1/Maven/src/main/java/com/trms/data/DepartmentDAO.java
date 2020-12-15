package com.trms.data;

import com.trms.beans.Department;

public interface DepartmentDAO extends GenericDAO<Department> {
	public Department add (Department d);
	public Department getByName(String name);

}
