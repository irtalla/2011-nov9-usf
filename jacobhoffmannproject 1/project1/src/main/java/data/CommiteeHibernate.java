package data;

import java.util.HashSet;
//import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Commitee;
import models.Employee;
import models.Pitch_Status;
import utils.HibernateUtil;

public class CommiteeHibernate implements CommiteeDao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Commitee getCommiteeById(Integer id) {
		Session s = hu.getSession();
		Commitee e = s.get(Commitee.class,id);
		s.close();
		return e;
	}

	public Set<Commitee> getByGenreID(Integer i) {
		Session s = hu.getSession();
		String query = "FROM Commitee where genre.id = :status";
		Query<Commitee> q = s.createQuery(query, Commitee.class);
		q.setParameter("status", i);
		List<Commitee> comitList = q.getResultList();
		Set<Commitee> comitSet = new HashSet<Commitee>();
		comitSet.addAll(comitList);
		s.close();
		return comitSet;
	}

	public Set<Commitee> getByEditorId(Integer i) {
		Session s = hu.getSession();
		String query = "FROM Commitee where editor_id.id = :status";
		Query<Commitee> q = s.createQuery(query, Commitee.class);
		q.setParameter("status", i);
		List<Commitee> comitList = q.getResultList();
		Set<Commitee> comitSet = new HashSet<Commitee>();
		comitSet.addAll(comitList);
		s.close();
		return comitSet;
	}
	public Set<Commitee> getAll() {
		Session s = hu.getSession();
		String query = "FROM Commitee";
		Query<Commitee> q = s.createQuery(query, Commitee.class);
		List<Commitee> catsList = q.getResultList();
		Set<Commitee> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}
	public Commitee add(Commitee e) {
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

	public void updateCommitee(Commitee e) {
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
