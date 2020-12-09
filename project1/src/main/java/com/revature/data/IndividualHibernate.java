package com.revature.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import com.revature.utils.HibernateUtil;
import com.revature.beans.Individual;

public class IndividualHibernate implements IndividualDAO {

private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Individual add(Individual t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Individual> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Individual t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Individual t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Individual getByUsername(String username) {
		try{
			// Criteria API: a way of making queries in a programmatic syntax
			Session s = hu.getSession();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Individual> criteria = cb.createQuery(Individual.class);
			Root<Individual> root = criteria.from(Individual.class);
			
			Predicate predicateForUsername = cb.equal(root.get("name"), username);
			// Predicate predicateForPassword = cb.equal(root.get("passwd"), password);
			// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
			
			criteria.select(root).where(predicateForUsername);
			
			Individual p = s.createQuery(criteria).getSingleResult();
			return p;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
