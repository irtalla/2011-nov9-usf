package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cross.beans.Comment;
import com.cross.beans.Decision;
import com.cross.utils.HibernateUtil;

public class DecisionHibernate implements DecisionDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Decision getById(Integer id) {
		Session s = hu.getSession();
		Decision c = s.get(Decision.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Decision> getAll() {
		Session s = hu.getSession();
		String query = "FROM Decision";
		Query<Decision> q = s.createQuery(query, Decision.class);
		List<Decision> decisionsList = q.getResultList();
		Set<Decision> decisionsSet = new HashSet<>();
		decisionsSet.addAll(decisionsList);
		s.close();
		return decisionsSet;
	}
	
	@Override
	public boolean update(Decision t) {
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
	public boolean delete(Decision t) {
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
	public Decision add(Decision c) {
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
	public Set<Comment> getByEditorId(Integer editorId) {

		Session s = hu.getSession();
		String query = "FROM Comment where editor_id = :id";
		Query<Comment> q = s.createQuery(query, Comment.class);
		q.setParameter("id", editorId);
		List<Comment> commentsList = q.getResultList();
		Set<Comment> commentsSet = new HashSet<>();
		commentsSet.addAll(commentsList);
		s.close();
		return commentsSet;
	}
	
	@Override
	public Set<Comment> getByPitchId(Integer pitchId) {

		Session s = hu.getSession();
		String query = "FROM Comment where pitch_id = :id";
		Query<Comment> q = s.createQuery(query, Comment.class);
		q.setParameter("id", pitchId);
		List<Comment> commentsList = q.getResultList();
		Set<Comment> commentsSet = new HashSet<>();
		commentsSet.addAll(commentsList);
		s.close();
		return commentsSet;
	}
	
	
}
