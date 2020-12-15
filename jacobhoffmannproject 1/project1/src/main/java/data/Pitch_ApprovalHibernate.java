package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Draft;
import models.Employee;
import models.Pitch_Approval;
import utils.HibernateUtil;

public class Pitch_ApprovalHibernate implements Pitch_ApprovalDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Pitch_Approval getById(Integer id) {
		Session s = hu.getSession();
		Pitch_Approval e = s.get(Pitch_Approval.class,id);
		s.close();
		return e;
	}

	public Pitch_Approval add(Pitch_Approval e) {
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

	public void update(Pitch_Approval e) {
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
	public Set<Pitch_Approval> getByEditorId(Integer id) {
		Session s = hu.getSession();
	
		String query = "FROM Pitch_Approval where editor.id = :status";
		Query<Pitch_Approval> q = s.createQuery(query, Pitch_Approval.class);
		q.setParameter("status", id);
		List<Pitch_Approval> paList = q.getResultList();
		Set<Pitch_Approval> paSet = new HashSet<Pitch_Approval>();
		paSet.addAll(paList);
		s.close();
		return paSet;
	}


}
