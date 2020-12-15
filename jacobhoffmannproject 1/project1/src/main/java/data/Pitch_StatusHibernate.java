package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Employee;
import models.Pitch_Approval;
import models.Pitch_Status;
import utils.HibernateUtil;

public class Pitch_StatusHibernate implements Pitch_StatusDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Pitch_Status getById(Integer id) {
		Session s = hu.getSession();
		Pitch_Status e = s.get(Pitch_Status.class,id);
		s.close();
		return e;
	}

	public Pitch_Status add(Pitch_Status e) {
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

	public void update(Pitch_Status e) {
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
	public Set<Pitch_Status> getByEditorId(Integer id) {
		Session s = hu.getSession();
		
		String query = "FROM Pitch_Status where genre_status.editor.id = :status or outside_status.editor.id = :status or assitant_status.editor.id = :status";
		Query<Pitch_Status> q = s.createQuery(query, Pitch_Status.class);
		q.setParameter("status", id);
		List<Pitch_Status> paList = q.getResultList();
		Set<Pitch_Status> paSet = new HashSet<Pitch_Status>();
		paSet.addAll(paList);
		s.close();
		return paSet;
	}

	@Override
	public Set<Pitch_Status> getAll() {
		Session s = hu.getSession();
		String query = "FROM Pitch_Status";
		Query<Pitch_Status> q = s.createQuery(query, Pitch_Status.class);
		List<Pitch_Status> catsList = q.getResultList();
		Set<Pitch_Status> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}

	@Override
	public Set<Pitch_Status> getByUserId(Integer id) {
		Session s = hu.getSession();
		
		String query = "FROM Pitch_Status where story.author.id = :status";
		Query<Pitch_Status> q = s.createQuery(query, Pitch_Status.class);
		q.setParameter("status", id);
		List<Pitch_Status> paList = q.getResultList();
		Set<Pitch_Status> paSet = new HashSet<Pitch_Status>();
		paSet.addAll(paList);
		s.close();
		return paSet;
	}

}
