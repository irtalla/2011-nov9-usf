package dev.rev.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import dev.rev.beans.event;
import dev.rev.beans.reimbForm;
import dev.rev.utils.HibernateUtil;

public class formhuber implements formDAO {


	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public reimbForm add(reimbForm t) throws Exception {
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
	public reimbForm getById(Integer id) {
		Session s= hu.getSession();
		reimbForm form=s.get(reimbForm.class, id);
		s.close();
		return form;
	}



	@Override
	public void update(reimbForm t) {
		// TODO Auto-generated method stub
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
	public void delete(reimbForm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<reimbForm> getempforms(int emp_id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<reimbForm> criteria = cb.createQuery(reimbForm.class);
		Root<reimbForm> root = criteria.from(reimbForm.class);
		
		Predicate predicateForUsername = cb.equal(root.get("emp_id"), emp_id);
		// Predicate predicateForPassword = cb.equal(root.get("passwd"), password);
		// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
		
		criteria.select(root).where(predicateForUsername);
		
		List<reimbForm> p = s.createQuery(criteria).getResultList();
		return p;
	}
	@Override
	public List<reimbForm> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String query = "FROM reimbForm";
		Query<reimbForm> q = s.createQuery(query, reimbForm.class);
		List<reimbForm> catsList =q.getResultList();
	
		s.close();
		return catsList ;
	}

}
