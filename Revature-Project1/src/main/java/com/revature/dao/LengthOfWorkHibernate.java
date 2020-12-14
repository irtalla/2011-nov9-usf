package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Genre;
import com.revature.beans.LengthOfWork;
import com.revature.utils.SessionUtil;

public class LengthOfWorkHibernate implements LengthOfWorkDAO {

	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public LengthOfWork getLengthOfWorkByName(String name) {
		Session s = su.getSession();
		
		String query = "FROM LengthOfWork where name = :wlt";
		Query<LengthOfWork> q = s.createQuery(query, LengthOfWork.class);
		q.setParameter("wlt", name);
		List<LengthOfWork> wltList = q.getResultList();
		s.close();
		
		//assumption: there is only one length of literary work to a name.
		//barring any strange errors, this is true.
		if (wltList.size() > 0) {
			return wltList.get(0);
		}
		
		return null;
	}

}
