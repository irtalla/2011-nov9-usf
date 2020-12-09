package dev.elliman.data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dev.elliman.beans.Person;
import dev.elliman.utils.HibernateUtil;

public class PersonHibernatePostgres implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Person getPersonByUsername(String username) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		
		criteria.select(root).where(predicateForUsername);
		
		Person p;
		try {
			p = s.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			p = null;
		}
		s.close();
		return p;
	}

}
