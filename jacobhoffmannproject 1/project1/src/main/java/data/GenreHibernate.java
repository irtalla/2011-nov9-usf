package data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Employee;
import models.Genre;
import utils.HibernateUtil;

public class GenreHibernate implements Genre_Dao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Genre getById(Integer id) {
		Session s = hu.getSession();
		Genre e = s.get(Genre.class,id);
		s.close();
		return e;
	}

	public Genre add(Genre e) {
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

	public void update(Genre e) {
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
