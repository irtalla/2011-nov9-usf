package data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Employee;
import models.Story_Type;
import utils.HibernateUtil;

public class Story_TypeHibernate implements Story_TypeDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Story_Type getById(Integer id) {
		Session s = hu.getSession();
		Story_Type e = s.get(Story_Type.class,id);
		s.close();
		return e;
	}

	public Story_Type add(Story_Type e) {
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

	public void update(Story_Type e) {
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
