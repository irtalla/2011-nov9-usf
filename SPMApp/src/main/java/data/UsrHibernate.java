package data;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import beans.Committee;
import beans.Genre;
import beans.Pitch;
import beans.Story_type;
import beans.Usr;
import exceptions.NonUniqueUsernameException;
import utils.HibernateUtil;

public class UsrHibernate implements UsrDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Usr getById(Integer id) {
		Session s = hu.getSession();
		Usr u = s.get(Usr.class, id);
		s.close();
		return u;
	}

	@Override
	public Set<Usr> getAll() {
		Session s = hu.getSession();
		String query = "FROM Usr";
		Query<Usr> q = s.createQuery(query, Usr.class);
		List<Usr> usrList = q.getResultList();
		Set<Usr> usrSet = new HashSet<>();
		usrSet.addAll(usrList);
		s.close();
		return usrSet;
	}

	@Override
	public void update(Usr t) {
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
	public void delete(Usr t) {
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
	public Usr add(Usr u) throws NonUniqueUsernameException {
		Session s = hu.getSession();
		Transaction tx = null;
		try { tx = s.beginTransaction();
			s.save(u);
			tx.commit();
	} catch (Exception e) {
		if (tx != null)
			tx.rollback();
	} finally {
		s.close();
	}
		return u;
}

	@Override
	public Usr getByUsrname (String username) {
	System.out.println("getBy reached" + "" + username);
	
	Session s = hu.getSession();
	CriteriaBuilder cb = s.getCriteriaBuilder();
	CriteriaQuery<Usr> criteria = cb.createQuery(Usr.class);
	Root<Usr> root = criteria.from(Usr.class);

	Predicate predicateForUsername = cb.equal(root.get("usrname"), username);
	// Predicate predicateForPassword = cb.equal(root.get("passwd"), password);
	// Predicate predicateForBoth = cb.and(predicateForUsername, predicateForPassword);
	criteria.select(root).where(predicateForUsername);
	Usr p = s.createQuery(criteria).getSingleResult();
	System.out.println(p);
	return p;
	
	}
	@Override
	public Set<Usr> getUsrsByCommittee(Committee c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Story_type> getTypes() {
		Session s = hu.getSession();
		String query = "FROM Story_type";
		Query<Story_type> q = s.createQuery(query, Story_type.class);
		List<Story_type> typeList = q.getResultList();
		Set<Story_type> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public Set<Genre> getGenres() {
		Session s = hu.getSession();
		String query = "FROM Genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genreList = q.getResultList();
		Set<Genre> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

}
