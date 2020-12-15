package data;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Approval_Other;
import models.Employee;
import utils.HibernateUtil;

public class Approval_OtherHibernate implements ApprovalOtherDao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	public Approval_Other getApprovalById(Integer id) {
		Session s = hu.getSession();
		Approval_Other e = s.get(Approval_Other.class,id);
		s.close();
		return e;
	}

	public Approval_Other getByEditorId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM approval_other where editor_id = :status";
		Query<Approval_Other> q = s.createQuery(query, Approval_Other.class);
		q.setParameter("status", i);
		Approval_Other emp = q.getSingleResult();
		s.close();
		return emp;
	}

	public Approval_Other add(Approval_Other e) {
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

	public void updateApproval(Approval_Other e) {
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

}
