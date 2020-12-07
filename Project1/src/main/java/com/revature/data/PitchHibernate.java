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
import org.hibernate.query.Query;

import com.cross.beans.Pitch;
import com.cross.beans.Pitch;
import com.cross.utils.HibernateUtil;

public class PitchHibernate implements PitchDAO {
	
private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Pitch getById(Integer id) {
		Session s = hu.getSession();
		Pitch c = s.get(Pitch.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Pitch> getAll() {
		Session s = hu.getSession();
		String query = "FROM Pitch";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}
	
	@Override
	public boolean update(Pitch t) {
		Boolean didUpdate = false; 
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
			didUpdate = true; 
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				didUpdate = false; 
			}
		} finally {
			s.close();
		}
		return didUpdate; 
	}
	
	@Override
	public boolean delete(Pitch t) {
		Boolean didDelete = false; 
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
			didDelete = true; 
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				didDelete = false; 
			}
		} finally {
			s.close();
		}
		return didDelete; 
	}
	
	@Override
	public Pitch add(Pitch c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				// Give indication of failure with null return value
				c = null; 
			}
		} finally {
			s.close();
		}
		return c;
	}
	
	@Override
	public Set<Pitch> getByAuthorId(Integer pitchId) {

		Session s = hu.getSession();
		String query = "FROM Pitch where author_id = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", pitchId);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}

	@Override
	public Set<Pitch> getByGenre(String genreName) {

		Session s = hu.getSession();
		String query = "FROM Pitch where genre.name = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", genreName);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}
	
	@Override
	public Set<Pitch> getByStatus(String statusName) {

		Session s = hu.getSession();
		String query = "FROM Pitch where status.name = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", statusName);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}
	
	@Override
	public Set<Pitch> getByStage(String stageName) {

		Session s = hu.getSession();
		String query = "FROM Pitch where stage.name = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", stageName);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}
	
	@Override
	public Set<Pitch> getByGeneralEditorId(String generalEditorId) {

		Session s = hu.getSession();
		String query = "FROM Pitch where general_editor_id = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", generalEditorId);
		List<Pitch> pitchsList = q.getResultList();
		Set<Pitch> pitchsSet = new HashSet<>();
		pitchsSet.addAll(pitchsList);
		s.close();
		return pitchsSet;
	}
		
	
}
