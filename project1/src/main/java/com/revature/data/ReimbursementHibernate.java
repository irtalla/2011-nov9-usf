package com.revature.data;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReimbursementHibernate implements ReimbursementDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public Reimbursement add(Reimbursement r) throws Exception {
		// TODO Auto-generated method stub
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
	public Reimbursement getById(Integer id) {
		Reimbursement r = new Reimbursement();
		
		Session s = hu.getSession();
		String query = "FROM Reimbursement where id = :id";
		Query<Reimbursement> q = s.createQuery(query, Reimbursement.class);
		q.setParameter("id", id);
		r = q.getSingleResult();
		s.close();
		
		return r;
	}

	@Override
	public Set<Reimbursement> getAll() {
		Session s = hu.getSession();
		String query = "FROM Reimbursement";
		Query<Reimbursement> q = s.createQuery(query, Reimbursement.class);
		List<Reimbursement> rList = q.getResultList();
		Set<Reimbursement> rSet = new HashSet<>();
		rSet.addAll(rList);
		s.close();
		return rSet;
	}

	@Override
	public void update(Reimbursement t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Reimbursement t) {
		// TODO Auto-generated method stub

	}
	@Override
	public Set<Reimbursement> getReimbursementsByUserId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Reimbursement where employee.id = :id";
		Query<Reimbursement> q = s.createQuery(query, Reimbursement.class);
		q.setParameter("id", id);
		List<Reimbursement> rList = q.getResultList();
		Set<Reimbursement> rSet = new HashSet<>();
		rSet.addAll(rList);
		s.close();
		return rSet;
	}
	public Reimbursement updateReimbursement(Reimbursement r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	
		return r;
	}
}
