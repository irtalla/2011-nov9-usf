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

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate extends GenericHibernate<Person> implements PersonDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public PersonHibernate() {
		super(Person.class);
	}
	
//	@Override
//	public void add(Person p) {
//		Session s = hu.getSession();
//		Transaction tx = null;
//		try {
//			tx = s.beginTransaction();
//			s.save(p);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//		} finally {
//			s.close();
//		}
//		return;
//	}

	@Override
	public void addPerson(Person p) throws NonUniqueUsernameException {
		try {
			this.add(p);
		}catch(Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePerson(Person p) throws NonUniqueUsernameException {
		try {
			this.add(p);
		}catch(Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
	}
	
	public Person getByUsername(String username) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		
		criteria.select(root).where(predicateForUsername);
		
		Person p = s.createQuery(criteria).getSingleResult();
		return p;
	}

	public Set<Person> getAllPeopleWithRole(Role role) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForRole = cb.equal(root.get("role"), role);
		
		criteria.select(root).where(predicateForRole);
		
		List<Person> people = s.createQuery(criteria).getResultList();
		return new HashSet<>(people);
	}

	public Set<Person> getAllEditorsWithRoleAndGenre(Role role, Genre genre) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForRole = cb.equal(root.get("role"), role);
		Predicate predicateForGenre = cb.equal(root.get("genre"), genre);
		
		criteria.select(root).where(predicateForRole, predicateForGenre);
		
		List<Person> people = s.createQuery(criteria).getResultList();
		return new HashSet<>(people);
	}

}
