package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.utils.HibernateUtil;

public class PitchHibernate implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Pitch getById(Integer id) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Pitch> criteriaPitch = cb.createQuery(Pitch.class);
		Root<Pitch> rootPitch = criteriaPitch.from(Pitch.class);
		
		Predicate predicateForId = cb.equal(rootPitch.get("id"), id);
		criteriaPitch.select(rootPitch).where(predicateForId);
		
		Pitch p = s.createQuery(criteriaPitch).getSingleResult();
		return p;
	}

	@Override
	public Set<Pitch> getAll() {
		Session s = hu.getSession();
		String query = "FROM Pitch";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		List<Pitch> pitchesList = q.getResultList();
		Set<Pitch> pitchesSet = new HashSet<>();
		pitchesSet.addAll(pitchesList);
		s.close();
		return pitchesSet;
	}

	@Override
	public void update(Pitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		System.out.println("Transaction started.");
		try {
			System.out.println("Trying to transact.");
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Transaction failed.");
		} finally {
			System.out.println("Transaction complete.");
			s.close();
		}
	}

	@Override
	public void delete(Pitch t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pitch add(Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return p;
	}
}
