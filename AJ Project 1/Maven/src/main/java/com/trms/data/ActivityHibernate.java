package com.trms.data;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.trms.beans.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trms.utils.HibernateUtil;

import com.trms.beans.Activity;

public class ActivityHibernate implements ActivityDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Activity getById(Integer id) {
		Session s = hu.getSession();
		Activity a = s.get(Activity.class, id);
		s.close();
		return a;
	}

	@Override
	public List<Activity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Activity t) {
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
	public void delete(Activity t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Activity add(Activity a) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return a;
	}

	@Override
	public List<Activity> getByReqId(Integer id) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Activity> criteria = cb.createQuery(Activity.class);
		Root<Activity> root = criteria.from(Activity.class);
		
		Predicate predicateForId = cb.equal(root.get("request_id"), id);
		
		criteria.select(root).where(predicateForId);
		List<Activity> activityList = new ArrayList<>();
		activityList = s.createQuery(criteria).getResultList();
//		activityList.add(a);
		return activityList;
	}

}
