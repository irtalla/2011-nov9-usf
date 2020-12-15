package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Draft_Approval;
import models.Employee;
import utils.HibernateUtil;

public class Draft_ApprovalHibernate implements Draft_Approval_Dao {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Draft_Approval getDAById(Integer id) {
		Session s = hu.getSession();
		Draft_Approval e = s.get(Draft_Approval.class,id);
		s.close();
		return e;
	}

	public Draft_Approval getByStoryId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM draft_approval where story_id = :status";
		Query<Draft_Approval> q = s.createQuery(query, Draft_Approval.class);
		q.setParameter("status", i);
		Draft_Approval emp = q.getSingleResult();
		s.close();
		return emp;
	}

	public Draft_Approval add(Draft_Approval e) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(e);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return e;
	}

	public void updateDraft_Approval(Draft_Approval e) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(e);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public Draft_Approval getById(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Draft_Approval> getByEditorId(Integer i) {
		Session s = hu.getSession();
		
		String query = "FROM Draft_Approval where editor.id = :status";
		Query<Draft_Approval> q = s.createQuery(query, Draft_Approval.class);
		q.setParameter("status", i);
		List<Draft_Approval> paList = q.getResultList();
		Set<Draft_Approval> paSet = new HashSet<Draft_Approval>();
		paSet.addAll(paList);
		s.close();
		return  paSet;
	}

}
