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
	private Class<T> type;
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public GenericHibernate(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public T getById(Integer id) {
		Session s = hu.getSession();
		T t = s.get(this.type, id);
		s.close();
		return t;
	}

	@Override
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

	@Override
	public T update(T t) {
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
		return t;
	}

	@Override
	public void delete(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public T add(T t) {
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
}
