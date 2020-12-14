package dev.rev.data;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;


import dev.rev.beans.employee;
import dev.rev.utils.HibernateUtil;

public class employeehiber implements employeeDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public employee add(employee t) throws Exception {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return t;
	}

	@Override
	public employee getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		employee c = s.get(employee.class, id);
		s.close();
		return c;
	}

	@Override
	public List<employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(employee t){
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public employee getbyusername(String name) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<employee> criteria = cb.createQuery(employee.class);
		Root<employee> root = criteria.from(employee.class);
		
		Predicate predicateForUsername = cb.equal(root.get("emp_name"), name);
		// Predicate predicateForPassword = cb.equal(root.get("passwd"), password);
		// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
		
		criteria.select(root).where(predicateForUsername);
			
		employee p = s.createQuery(criteria).uniqueResult();
		return p;
	}

}
