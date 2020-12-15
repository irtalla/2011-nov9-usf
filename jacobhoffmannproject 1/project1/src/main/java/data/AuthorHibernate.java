package data;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Approval_Other;
import models.Author;
import utils.HibernateUtil;

public class AuthorHibernate implements AuthorDao {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Author getAuthorById(Integer id) {
		Session s = hu.getSession();
		Author e = s.get(Author.class,id);
		s.close();
		return e;
	}

	public Author getByUserId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM Author where user_id = :status";
		Query<Author> q = s.createQuery(query, Author.class);
		q.setParameter("status", i);
		Author emp = q.getSingleResult();
		s.close();
		return emp;
	}

	public Author add(Author e) {
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

	public void updateAuthor(Author e) {
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
