package com.revature.data;

import com.revature.beans.Person;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Draft;
//import com.revature.beans.Person;
import com.revature.utils.HibernateUtil;

public class DraftHibernate implements DraftDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();


    @Override
    public Draft add(Draft d) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(d);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return d;
    }

    @Override
    public Draft getById(Integer id) {
        Session s = hu.getSession();
        Draft d = s.get(Draft.class, id);
        s.close();
        return d;
    }

    @Override
    public Set<Draft> getAll() {
        Session s = hu.getSession();
        String query = "from draft";
        Query<Draft> q = s.createQuery(query, Draft.class);
        List<Draft> draftList = q.getResultList();
        Set<Draft> draftSet = new HashSet<>();
        draftSet.addAll(draftList);
        s.close();
        return draftSet;
    }

    @Override
    public void update(Draft t) {
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
    public void delete(Draft t) {
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
    public Set<Draft> getPending() {
    	Session s = hu.getSession();
		String query = "from Draft where status.name = :status";
		Query<Draft> q = s.createQuery(query, Draft.class);
		q.setParameter("status", "Available");
		List<Draft> draftsList = q.getResultList();
		Set<Draft> draftsSet = new HashSet<>();
		draftsSet.addAll(draftsList);
		s.close();
		return draftsSet;
    }

	 @Override
	 public Set<Draft> getByAuthor(Person author) {
	  Session s = hu.getSession();
	  String query = "FROM Draft where author = :author";
	  Query<Draft> q = s.createQuery(query, Draft.class);
	  q.setParameter("author", author);
	  List<Draft> draftsList = q.getResultList();
	  Set<Draft> draftsSet = new HashSet<>();
	  draftsSet.addAll(draftsList);
	  s.close();
	  return draftsSet;
	 }
}
