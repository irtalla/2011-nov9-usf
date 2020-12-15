package com.trms.data;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.trms.beans.Request;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.trms.utils.HibernateUtil;
import com.trms.beans.Department;

public class DepartmentHibernate implements DepartmentDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Department getById(Integer id) {
		Session s = hu.getSession();
		Department d = s.get(Department.class, id);
		s.close();
		return d;
	}

	@Override
	public List<Department> getAll() {
 		Session s = hu.getSession();
		String query = "from status";
		Query<Department> a = s.createQuery(query, Department.class);
		List<Department> list = new ArrayList();
		list = a.getResultList();

		s.close();
		return list;
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Department t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department add(Department d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getByName(String name) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
		Root<Department> root = criteria.from(Department.class);
		
		Predicate predicateForName = cb.equal(root.get("name"), name);
		
		criteria.select(root).where(predicateForName);
		Department p = s.createQuery(criteria).getSingleResult();
		return p;
	}

}
