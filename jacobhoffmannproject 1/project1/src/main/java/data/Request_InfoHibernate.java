package data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Employee;
import models.Request_Info;
import utils.HibernateUtil;

public class Request_InfoHibernate implements Request_Info_Dao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Request_Info getById(Integer id) {
		Session s = hu.getSession();
		Request_Info e = s.get(Request_Info.class,id);
		s.close();
		return e;
	}

	public Request_Info add(Request_Info e) {
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

	public void update(Request_Info e) {
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
