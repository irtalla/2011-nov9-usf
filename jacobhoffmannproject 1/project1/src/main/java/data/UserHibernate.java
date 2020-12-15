package data;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import exceptions.NonUniqueUsernameException;
import models.Users;
import utils.HibernateUtil;

public class UserHibernate implements User_Dao {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	public Users add(Users U) throws NonUniqueUsernameException {
		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(U);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return U;
	}

	public Users getByUsername(String username) {
		Session s = hu.getSession();
		String query = "from Users where username = :status";
		Query<Users> q = s.createQuery(query, Users.class);
		q.setParameter("status", username);
		Users user = q.getSingleResult();
		s.close();
		return user;

	}

	public Users getUserById(Integer id) {
		Session s = hu.getSession();
		Users u = s.get(Users.class,id);
		s.close();
		return u;
	}

	public void updateUser(Users u) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(u);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		
	}

}
