package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Approval;
import com.revature.utils.HibernateUtil;

public class ApprovalHibernate implements ApprovalDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Approval add(Approval t) {
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
	public Approval getById(Integer id) {
		Session s = hu.getSession();
		Approval form = s.get(Approval.class, id);
		s.close();
		return form;
	}

	@Override
	public Set<Approval> getAll() {
		Session s = hu.getSession();
		String query = "FROM Approval";
		Query<Approval> q = s.createQuery(query, Approval.class);
		List<Approval> typeList = q.getResultList();
		Set<Approval> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(Approval t) {
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
	public void delete(Approval t) {
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
