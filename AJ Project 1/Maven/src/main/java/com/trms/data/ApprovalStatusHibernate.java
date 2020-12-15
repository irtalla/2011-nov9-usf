package com.trms.data;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.trms.beans.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trms.utils.HibernateUtil;

import com.trms.beans.ApprovalStatus;

public class ApprovalStatusHibernate implements ApprovalStatusDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public ApprovalStatus getById(Integer id) {
		Session s = hu.getSession();
		ApprovalStatus a = s.get(ApprovalStatus.class, id);
		s.close();
		return a;
	}

	@Override
	public List<ApprovalStatus> getAll() {
		return null;
	}




	@Override
	public void update(ApprovalStatus t) {
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
	public void delete(ApprovalStatus t) {
		// TODO Auto-generated method stub

	}

	@Override
	public ApprovalStatus add(ApprovalStatus a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApprovalStatus getByName(String name) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<ApprovalStatus> criteria = cb.createQuery(ApprovalStatus.class);
		Root<ApprovalStatus> root = criteria.from(ApprovalStatus.class);
		
		Predicate predicateForName = cb.equal(root.get("name"), name);
		
		criteria.select(root).where(predicateForName);
		ApprovalStatus p = s.createQuery(criteria).getSingleResult();
		return p;
	}

}
