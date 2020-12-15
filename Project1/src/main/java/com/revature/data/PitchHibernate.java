package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Status;
import com.revature.utils.HibernateUtil;

public class PitchHibernate implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Pitch getById(Integer id) {
		Session s = hu.getSession();
		Pitch p = s.get(Pitch.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<Pitch> getAll() {
		Session s = hu.getSession();
		String query = "from Pitch";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		List<Pitch> pitchList = q.getResultList();
		Set<Pitch> pitchSet = new HashSet<>();
		pitchSet.addAll(pitchList);
		s.close();
		return pitchSet;
	}

	@Override
	public void update(Pitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(t);
			tx.commit();
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
	}

	@Override
	public void delete(Pitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		}
		catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
	}

	@Override
	public Pitch add(Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		}
		catch(Exception e){
			if(tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
		return p;
	}

	@Override
	public Set<Pitch> getByStatus(String status) {
		Session s = hu.getSession();
		String query = "FROM Pitch where status.name = :status";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("status", status);
		List<Pitch> pitchesList = q.getResultList();
		Set<Pitch> pitchesSet = new HashSet<>();
		pitchesSet.addAll(pitchesList);
		s.close();
		return pitchesSet;
	}

	@Override
	public Set<Pitch> getByAuthor(Person author) {
		Session s = hu.getSession();
		String query = "FROM Pitch where author = :author";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("author", author);
		List<Pitch> pitchesList = q.getResultList();
		Set<Pitch> pitchesSet = new HashSet<>();
		pitchesSet.addAll(pitchesList);
		s.close();
		return pitchesSet;
	}
}
