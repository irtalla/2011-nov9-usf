package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.EventAttatchment;
import com.revature.utils.HibernateUtil;

public class AttatchmentHibernate implements AttatchmentDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public EventAttatchment add(EventAttatchment t) {
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
	public EventAttatchment getById(Integer id) {
		//not implemented
		return null;
	}

	@Override
	public Set<EventAttatchment> getAll() {
		Session s = hu.getSession();
		String query = "FROM EventAttatchment";
		Query<EventAttatchment> q = s.createQuery(query, EventAttatchment.class);
		List<EventAttatchment> typeList = q.getResultList();
		Set<EventAttatchment> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(EventAttatchment t) {
		//not implemented

	}

	@Override
	public void delete(EventAttatchment t) {
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
	public Set<EventAttatchment> getEventAttatchmentByEventId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM EventAttatchment WHERE eventId = :eventId";
		Query<EventAttatchment> q = s.createQuery(query, EventAttatchment.class);
		q.setParameter("eventId", id);
		List<EventAttatchment> l = q.getResultList();
		Set<EventAttatchment> set = new HashSet<>();
		set.addAll(l);
		return set;
	}

}
