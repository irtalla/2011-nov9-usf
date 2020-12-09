package com.revature.data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.cross.beans.Comment;


public class CommentHibernateTest {
	
	private static CommentDAO commentDAO = new CommentHibernate(); 
	
	@DisplayName("addTest")
	@Test
	@Order(1) 
	public void addTest() {
		
		Comment c1 = new Comment(), c2 = new Comment(), c3 = new Comment(); 
		
		c1.setCommenterId(-1);
		c1.setContent("This is a comment string");
		c1.setId(1);
		c1.setRequestId(-1);
		Timestamp ts = new Timestamp(0);
		
		
		// need comment and request rows, we will also create a pitch
		
	}

}

/*

	@Override
	public Comment getById(Integer id) {
		Session s = hu.getSession();
		Comment c = s.get(Comment.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Comment> getAll() {
		Session s = hu.getSession();
		String query = "FROM Comment";
		Query<Comment> q = s.createQuery(query, Comment.class);
		List<Comment> commentsList = q.getResultList();
		Set<Comment> CommentsSet = new HashSet<>();
		CommentsSet.addAll(commentsList);
		s.close();
		return CommentsSet;
	}
	
	@Override
	public boolean update(Comment t) {
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
	public boolean delete(Comment t) {
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
	public Comment add(Comment c) {
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
	public Set<Comment> getByRequestId(Integer requestId) {
		
		Session s = hu.getSession();
		String query = "FROM Comment where request_id = :id";
		Query<Comment> q = s.createQuery(query, Comment.class);
		q.setParameter("id", requestId);
		List<Comment> commentsList = q.getResultList();
		Set<Comment> commentsSet = new HashSet<>();
		commentsSet.addAll(commentsList);
		s.close();
		return commentsSet;

	}

	@Override
	public Set<Comment> getByCommentorId(Integer commentorId) {

		Session s = hu.getSession();
		String query = "FROM Comment where commenter_id = :id";
		Query<Comment> q = s.createQuery(query, Comment.class);
		q.setParameter("id", commentorId);
		List<Comment> commentsList = q.getResultList();
		Set<Comment> commentsSet = new HashSet<>();
		commentsSet.addAll(commentsList);
		s.close();
		return commentsSet;
	}



*/