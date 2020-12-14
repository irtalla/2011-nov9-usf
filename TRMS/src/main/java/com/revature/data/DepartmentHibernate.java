package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Department;
import com.revature.utils.HibernateUtil;

public class DepartmentHibernate implements DepartmentDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Department add(Department t) {
		//not implemented
		return null;
	}

	@Override
	public Department getById(Integer id) {
		Session s = hu.getSession();
		Department d = s.get(Department.class, id);
		s.close();
		return d;
	}

	@Override
	public Set<Department> getAll() {
		Session s = hu.getSession();
		String query = "FROM Department";
		Query<Department> q = s.createQuery(query, Department.class);
		List<Department> typeList = q.getResultList();
		Set<Department> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(Department t) {
		//not implemented

	}

	@Override
	public void delete(Department t) {
		//not implemented
	}

}
