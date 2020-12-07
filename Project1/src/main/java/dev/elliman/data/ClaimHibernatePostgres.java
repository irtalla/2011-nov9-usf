package dev.elliman.data;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.utils.HibernateUtil;

public class ClaimHibernatePostgres implements ClaimDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public List<Claim> getClaimsByPerson(Person person) {
		
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Claim> criteria = cb.createQuery(Claim.class);
		Root<Claim> root = criteria.from(Claim.class);
		
		Predicate predicateForPerson = cb.equal(root.get("person"), person);
		
		criteria.select(root).where(predicateForPerson);
		
		List<Claim> claims = null;
		claims = s.createQuery(criteria).getResultList();
		
		return claims;
	}

	@Override
	public Claim makeClaim(Claim claim) {
		Session s = hu.getSession();
		
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(claim);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return claim;
	}

}
