package com.revature.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.utils.HibernateUtil;

public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Person getById(Integer id) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaPerson = cb.createQuery(Person.class);
		Root<Person> rootPerson = criteriaPerson.from(Person.class);
		
		Predicate predicateForId = cb.equal(rootPerson.get("id"), id);
		criteriaPerson.select(rootPerson).where(predicateForId);
		
		Person p = s.createQuery(criteriaPerson).getSingleResult();
		return p;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person add(Person p){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByUsername(String username) {
		// Criteria API: a way of making queries in a programmatic syntax
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaPerson = cb.createQuery(Person.class);
		Root<Person> rootPerson = criteriaPerson.from(Person.class);
		
		Predicate predicateForUsername = cb.equal(rootPerson.get("username"), username);
		// Predicate predicateForPassword = cb.equal(root.get("passwd"), password);
		// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
		criteriaPerson.select(rootPerson).where(predicateForUsername);
		
		Person p = s.createQuery(criteriaPerson).getSingleResult();
		return p;
	}

}