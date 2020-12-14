package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.InformationRequest;
import com.revature.utils.HibernateUtil;

public class InformationRequestHibernate implements InformationRequestDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public InformationRequest add(InformationRequest t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return t;
	}

	@Override
	public InformationRequest getById(Integer id) {
		Session s = hu.getSession();
		InformationRequest form = s.get(InformationRequest.class, id);
		s.close();
		return form;
	}

	@Override
	public Set<InformationRequest> getAll() {
		Session s = hu.getSession();
		String query = "FROM InformationRequest";
		Query<InformationRequest> q = s.createQuery(query, InformationRequest.class);
		List<InformationRequest> typeList = q.getResultList();
		Set<InformationRequest> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(InformationRequest t) {
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
	public void delete(InformationRequest t) {
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

}
