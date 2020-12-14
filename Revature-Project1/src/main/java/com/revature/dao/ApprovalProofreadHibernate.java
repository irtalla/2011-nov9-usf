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
import com.revature.beans.User;
import com.revature.utils.SessionUtil;

public class ApprovalProofreadHibernate implements ApprovalProofreadDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public boolean addProofreaders(List<Editor> proofreaders, StoryPitch work) {
		Session s = su.getSession();
		Transaction tx = null;
		boolean addedProofreaders = false;
		
		try {
			tx = s.beginTransaction();
			
			for(Editor e: proofreaders) {
				s.save(e);
			}
			
			tx.commit();
			addedProofreaders = true;
		}
		catch(Exception e) {
			if (tx != null) {
				tx.rollback();

			}
		}
		finally {
			s.close();
		}
		
		return addedProofreaders;
	}

	@Override
	public boolean removeProofreaders(int approvalID) {
		Session s = su.getSession();
		
		boolean removedProofreaders = true;
		String strangeReturn = "FROM ApprovalProofreaders where approval_id=:id ";
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
					removedProofreaders = false;
				}
			}
		}
		s.close();
		
		return removedProofreaders;
	}

	@Override
	public List<Editor> retrieveProofreadersForAPitch(int approvalID) {
		Session s = su.getSession();
		String query = "FROM ApprovalProofreaders where approvalID=:id";
		Query<ApprovalProofreaders> q = s.createQuery(query, ApprovalProofreaders.class);
		List<ApprovalProofreaders> proofreadersList = q.getResultList();
		s.close();
		List<Editor> actualProofreadersList = new ArrayList<Editor>();
		for (ApprovalProofreaders ap: proofreadersList) {
			actualProofreadersList.add(ap.getEditor());
		}
		
		return actualProofreadersList;
	}

}
