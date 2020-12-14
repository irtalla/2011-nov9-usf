package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.ApprovalProofreaders;
import com.revature.beans.Editor;
import com.revature.beans.FinalViewers;
import com.revature.beans.StoryPitch;
import com.revature.utils.SessionUtil;

public class FinalViewersHibernate implements FinalViewersDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public boolean addViewers(List<Editor> viewers, StoryPitch storyPitch) {
		Session s = su.getSession();
		Transaction tx = null;
		
		boolean addedViewers = false;
		
		try {
			tx = s.beginTransaction();
			
			for(Editor e: viewers) {
				s.save(e);
			}
			
			tx.commit();
			addedViewers = true;
		}
		catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
		
		return addedViewers;
	}

	@Override
	public boolean removeViewers(int approvalID) {
		Session s = su.getSession();
		
		boolean removeViewers = true;
		String strangeReturn = "FROM FinalViewers where approval_id=:id ";
		Query<ApprovalProofreaders> q = s.createQuery(strangeReturn, ApprovalProofreaders.class);
		q.setParameter("id", approvalID);
		List<ApprovalProofreaders> userList = q.getResultList();
		
		Transaction tx = s.beginTransaction();
		
		for (ApprovalProofreaders ap: userList) {
			try {
				s.delete(ap);
				tx.commit();
			}
			catch (Exception e) {
				if (tx != null) {
					tx.rollback();
					removeViewers = false;
				}
			}
		}
		s.close();
		
		return removeViewers;
	}

	@Override
	public List<Editor> retrieveAllViewersForASpecificPitch(int approvalID) {
		Session s = su.getSession();
		String query = "FROM FinalViewers where approvalID=:id";
		Query<FinalViewers> q = s.createQuery(query, FinalViewers.class);
		List<FinalViewers> finalViewersList = q.getResultList();
		s.close();
		List<Editor> actualFinalViewersList = new ArrayList<Editor>();
		for (FinalViewers fv: finalViewersList) {
			actualFinalViewersList.add(fv.getEditor());
		}
		
		return actualFinalViewersList;

	}

}
