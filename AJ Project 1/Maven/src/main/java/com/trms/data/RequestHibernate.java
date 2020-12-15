package com.trms.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trms.beans.Request;
import com.trms.utils.HibernateUtil;

public class RequestHibernate implements RequestDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Request getById(Integer id) {
		Session s = hu.getSession();
		Request r = s.get(Request.class, id);
		s.close();
		return r;
	}

	@Override
	public List<Request> getAll() {
 		Session s = hu.getSession();
		String query = "from Request";
		Query<Request> a = s.createQuery(query, Request.class);
		List<Request> list = new ArrayList();
		list = a.getResultList();
		s.close();
		return list;
	}

	@Override
	public void update(Request t) {
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
	public void delete(Request t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Request add(Request r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(r);
//			System.out.println(r.getId());
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return r;
	}
	
	public List<Request> getByRequestorId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Request where requestor.id = :id";
		Query<Request> q = s.createQuery(query, Request.class);
		q.setParameter("id", id);
		
		System.out.println(q.getResultList());
		List<Request> list = q.getResultList();
//		list = s.createQuery(criteria).getResultList();
//		list.add(r);
		s.close();
		return list;
	}

	@Override
	public List<Request> getByManagerId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Request where manager = :id";
		Query<Request> q = s.createQuery(query, Request.class);
		q.setParameter("id", id);

		System.out.println(q.getResultList());
		List<Request> list = q.getResultList();
//		list = s.createQuery(criteria).getResultList();
//		list.add(r);
		s.close();
		return list;
	}

	@Override
	public List<Request> getBydHeadId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Request where dHead = :id";
		Query<Request> q = s.createQuery(query, Request.class);
		q.setParameter("id", id);

		System.out.println(q.getResultList());
		List<Request> list = q.getResultList();
//		list = s.createQuery(criteria).getResultList();
//		list.add(r);
		s.close();
		return list;
	}

}
