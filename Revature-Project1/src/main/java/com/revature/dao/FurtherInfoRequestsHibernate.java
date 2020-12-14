package com.revature.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.beans.FurtherInfoRequests;
import com.revature.utils.SessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FurtherInfoRequestsHibernate implements FurtherInfoRequestsDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public void addFurtherInfoToAPitch(FurtherInfoRequests fir) {
		Session s = su.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(fir);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}

		
	}

	@Override
	public Set<FurtherInfoRequests> getFurtherInfo(int id, int stage) {
		Session s = su.getSession();
		String query = "FROM FurtherInfoRequests where id=:id and stageWhenRequested=:stage";
		Query<FurtherInfoRequests> q = s.createQuery(query, FurtherInfoRequests.class);
		List<FurtherInfoRequests> catsList = q.getResultList();
		Set<FurtherInfoRequests> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;

	}

}
