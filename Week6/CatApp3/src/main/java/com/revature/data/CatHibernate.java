package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.utils.HibernateUtil;

@Repository
public class CatHibernate implements CatDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Cat getById(Integer id) {
		Session s = hu.getSession();
		Cat c = s.get(Cat.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Cat> getAll() {
		Session s = hu.getSession();
		String query = "FROM Cat";
		Query<Cat> q = s.createQuery(query, Cat.class);
		List<Cat> catsList = q.getResultList();
		Set<Cat> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}

	@Override
	public void update(Cat t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Cat t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Cat add(Cat c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return c;
	}

	@Override
	public Set<Cat> getAvailableCats() {
		Session s = hu.getSession();
		String query = "FROM Cat where status.name = :status";
		Query<Cat> q = s.createQuery(query, Cat.class);
		q.setParameter("status", "Available");
		List<Cat> catsList = q.getResultList();
		Set<Cat> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}

	@Override
	public void adoptCat(Person p, Cat c) {
		Session s = hu.getSession();
		String sql = "call adopt_cat(:personid, :catid)";
		// NativeQuery<T> nq = s.createNativeQuery(sql ????);
		// nq.setParameter("personid", p.getId());
		// nq.setParameter("catid", c.getId());
		s.close();
	}

}
