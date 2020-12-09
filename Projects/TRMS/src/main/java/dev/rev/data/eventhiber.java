package dev.rev.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;


import dev.rev.beans.event;
import dev.rev.utils.HibernateUtil;

public class eventhiber implements eventDAO {
	
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Set<event> getallevent() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String query = "FROM event";
		Query<event> q = s.createQuery(query, event.class);
		List<event> catsList = q.getResultList();
		Set<event> catsSet = new HashSet<>();
		catsSet.addAll(catsList);
		s.close();
		return catsSet;
	}

	@Override
	public event getbyId(int ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
