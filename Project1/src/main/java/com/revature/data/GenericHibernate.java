package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

public class GenericHibernate<T> implements GenericDAO<T>{
	protected Class<T> type;
	protected HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public GenericHibernate(Class<T> type) {
		this.type = type;
	}
	
	public T getById(Integer id) {
		Session s = hu.getSession();
		T t = s.get(this.type, id);
		s.close();
		return t;
	}

	public Set<T> getAll() {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(this.type);
		Root<T> root = criteria.from(this.type);
		
		criteria.select(root);
		
		List<T> tList = s.createQuery(criteria).getResultList();
		s.close();
		return new HashSet<T>(tList);
	}

	public void update(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public void delete(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public Integer add(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Integer id = (Integer) s.save(t);
//			tx.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return null;
	}
}
