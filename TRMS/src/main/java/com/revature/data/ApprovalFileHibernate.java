package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.ApprovalFile;
import com.revature.utils.HibernateUtil;

public class ApprovalFileHibernate implements ApprovalFileDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public ApprovalFile add(ApprovalFile t) {
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
	public ApprovalFile getById(Integer id) {
		//not implemented
		return null;
	}

	@Override
	public Set<ApprovalFile> getAll() {
		Session s = hu.getSession();
		String query = "FROM ApprovalFile";
		Query<ApprovalFile> q = s.createQuery(query, ApprovalFile.class);
		List<ApprovalFile> typeList = q.getResultList();
		Set<ApprovalFile> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(ApprovalFile t) {
		//not implemented

	}

	@Override
	public void delete(ApprovalFile t) {
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
	public Set<ApprovalFile> getApprovalFileByFormId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM ApprovalFile WHERE formId = :formId";
		Query<ApprovalFile> q = s.createQuery(query, ApprovalFile.class);
		q.setParameter("formId", id);
		List<ApprovalFile> l = q.getResultList();
		Set<ApprovalFile> set = new HashSet<>();
		set.addAll(l);
		return set;
	}

}
