package data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Draft_Status_Article;
import models.Employee;
import utils.HibernateUtil;

public class DSAHibernate implements Draft_Status_Article_Dao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Draft_Status_Article getById(Integer id) {
		Session s = hu.getSession();
		Draft_Status_Article e = s.get(Draft_Status_Article.class,id);
		s.close();
		return e;
	}

	public Draft_Status_Article add(Draft_Status_Article e) {
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

	public void update(Draft_Status_Article e) {
		// TODO Auto-generated method stub
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
