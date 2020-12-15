package data;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Editor;
import models.Employee;
import utils.HibernateUtil;

public class Editor_Hibernate implements Editor_Dao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Editor getEditorById(Integer id) {
		Session s = hu.getSession();
		Editor e = s.get(Editor.class,id);
		s.close();
		return e;
	}

	public Editor getByEmployeeId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM Editor where employee_id = :status";
		Query<Editor> q = s.createQuery(query, Editor.class);
		q.setParameter("status", i);
		Editor e = q.getSingleResult();
		s.close();
		return e;
	}

	public Editor add(Editor e) {
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

	public void updateEditor(Editor e) {
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
