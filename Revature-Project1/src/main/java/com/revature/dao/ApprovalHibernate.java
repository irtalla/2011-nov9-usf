package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.StoryPitch;
import com.revature.utils.SessionUtil;

public class ApprovalHibernate implements ApprovalDAO {

	private SessionUtil su = SessionUtil.getHibernateUtil();
	
	@Override
	public void rejectAPitch(StoryPitch sp) {
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.update(sp);
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
	}
	
	@Override
	public void acceptAPitch(StoryPitch sp) {
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.update(sp);
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
	}


}
