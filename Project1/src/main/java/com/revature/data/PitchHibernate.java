package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Pitch;
import com.revature.utils.HibernateUtil;

public class PitchHibernate implements PitchDAO {
private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Pitch add(Pitch t) throws NullPointerException {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
		}finally {
			s.close();
		}
		return t;
	}

	@Override
	public Pitch getById(Integer id) throws NullPointerException {
		Session s = hu.getSession();
		Pitch c = s.get(Pitch.class,  id);
		s.close();
		return c;
	}

	@Override
	public Set<Pitch> getAll() {
		Session s = hu.getSession();
		String query = "FROM Pitch";
		//query.Query
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		List<Pitch> comList = q.getResultList();
		Set<Pitch> comSet = new HashSet<>();
		comSet.addAll(comList);
		s.close();
		
		return comSet;
	}

	@Override
	public void update(Pitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch(Exception e) {
			if(tx !=null) {
				tx.rollback();
			}
		}finally {
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
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
	}

}
