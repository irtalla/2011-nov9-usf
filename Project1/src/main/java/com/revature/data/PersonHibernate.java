package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Person getById(Integer id) {
		Session s = hu.getSession();
		Person p = s.get(Person.class, id);
		s.close();
		return p;
	}

	public Set<Person> getAll() {
		Session s = hu.getSession();
		String query = "from person";
		Query<Person> q = s.createQuery(query, Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> personSet = new HashSet<>();
		personSet.addAll(personList);
		s.close();
		return personSet;
	}

	public void update(Person t) {
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

	public void delete(Person t) {
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

	public Person add(Person p) throws NonUniqueUsernameException {
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

	public Person getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM Person where username = :username";
		Query<Person> q = s.createQuery(query, Person.class);
		q.setParameter("username", username);
		Person p = q.getSingleResult();
		s.close();
		return p;
	}
}
