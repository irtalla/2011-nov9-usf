package com.revature.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.User;
import com.revature.utils.HibernateUtil;

public class UserHibernate implements UserDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public User add(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate predicateForId = cb.equal(root.get("user_id"), id);
		criteria.select(root).where(predicateForId);
		User u = s.createQuery(criteria).getSingleResult();
		
		return u;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getByUsername(String username) {
		// a hibernate query
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"),username);
		//Predicate predicateForPassword = cb.equal(root.get("pwd"),);
		
		criteria.select(root).where(predicateForUsername);
		User u = s.createQuery(criteria).getSingleResult();
		return u;
	}

}
