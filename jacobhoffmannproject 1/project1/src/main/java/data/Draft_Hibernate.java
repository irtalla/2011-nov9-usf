package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Draft;
import models.Employee;
import utils.HibernateUtil;

public class Draft_Hibernate implements DraftDao {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Draft getById(Integer id) {
		Session s = hu.getSession();
		Draft e = s.get(Draft.class,id);
		s.close();
		return e;
	}

	public Set<Draft> getByAuthorId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM Draft where story_id.author.id = :status";
		Query<Draft> q = s.createQuery(query, Draft.class);
		q.setParameter("status", i);
		List<Draft> catsList = q.getResultList();
		Set<Draft> catsSet = new HashSet<Draft>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}

	public Draft add(Draft e) {

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

	public void update(Draft e) {	
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
