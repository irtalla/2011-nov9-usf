package com.revature.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Status;
import com.revature.beans.Work;
import com.revature.utils.SessionUtil;

public class ProposedWorkHibernate implements ProposedWorkDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public Work addProposedWork(Work w) {
		Session s = su.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.save(w);
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
		
		return w;
	}

	@Override
	public boolean removeProposedWork(Work w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProposedWorkStatus(Work w, Status s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Work> getAllWorksOfAuthor(int id) {
		Session s = su.getSession();
		String authorsWorksQuery = "FROM Work where author_id=:id";
		
		Query<Work> q = s.createQuery(authorsWorksQuery, Work.class);
		q.setParameter("id", id);
		List<Work> authorsWorksList = q.getResultList();
		Set<Work> authorsWorks = new HashSet<Work>();
		authorsWorks.addAll(authorsWorksList);
		
		
		return authorsWorks;
	}

}
