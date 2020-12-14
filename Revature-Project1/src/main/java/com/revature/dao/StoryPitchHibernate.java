package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Author;
import com.revature.beans.StoryPitch;
import com.revature.beans.Work;
import com.revature.utils.SessionUtil;

public class StoryPitchHibernate implements StoryPitchDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();
	
	public StoryPitch addStoryPitch(StoryPitch sp) {
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.save(sp);
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
		return sp;
	}
	
	@Override
	public boolean rejectAPitch(StoryPitch sp) {
		boolean hasBeenRejected = false;
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.update(sp);
			tx.commit();
			hasBeenRejected = true;
		}
		catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
		
		return hasBeenRejected;
	}
	
	@Override
	public boolean acceptAPitch(StoryPitch sp) {
		boolean hasBeenAccepted = false;
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.update(sp);
			tx.commit();
			hasBeenAccepted = true;
		}
		catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
		
		return hasBeenAccepted;
	}

	@Override
	public StoryPitch retrieveAPitch(int userID, int workID) {
		Session s = su.getSession();

		String query = "FROM StoryPitch where proposed_work_id=:id";
		Query<StoryPitch> q = s.createQuery(query, StoryPitch.class);
		List<StoryPitch> catsList = q.getResultList();
		s.close();
		return catsList.get(0);
	}


}
