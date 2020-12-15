package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Employee;
import models.Pitch_Approval;
import models.Story;
import utils.HibernateUtil;

public class StoryHibernate implements StoryDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Story getById(Integer id) {
		Session s = hu.getSession();
		Story e = s.get(Story.class,id);
		s.close();
		return e;
	}

	public Story add(Story e) {
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

	public void update(Story e) {
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

	@Override
	public Set<Story> getByUserId(Integer i) {
		Session s = hu.getSession();
		
		String query = "FROM Story where author.users.id = :status";
		Query<Story> q = s.createQuery(query, Story.class);
		q.setParameter("status", i);
		List<Story> paList = q.getResultList();
		Set<Story> paSet = new HashSet<Story>();
		paSet.addAll(paList);
		s.close();
		return paSet;
	}

}
