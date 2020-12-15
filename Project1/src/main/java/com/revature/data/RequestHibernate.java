package com.revature.data;

import com.revature.beans.Person;
import com.revature.beans.Request;
import com.revature.utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    public Set<Request> getAll() {
        Session s = hu.getSession();
        String query = "from request";
        Query<Request> q = s.createQuery(query, Request.class);
        List<Request> requestList = q.getResultList();
        Set<Request> requestSet = new HashSet<>();
        requestSet.addAll(requestList);
        s.close();
        return requestSet;
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
    public Request add(Request r) {
    	Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return r;
    }

    @Override
    public Set<Request> getRequestsByPerson(Person p) {
        Session s = hu.getSession();
        String query = "from Request where request.to_person = :person";
        Query<Request> q = s.createQuery(query, Request.class);
        q.setParameter("to_person", p);
        List<Request> requestList = q.getResultList();
        Set<Request> requestSet = new HashSet<>();
        requestSet.addAll(requestList);
        s.close();
        return requestSet;
    }
}
