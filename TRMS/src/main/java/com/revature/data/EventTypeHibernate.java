package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;


import com.revature.beans.EventType;
import com.revature.utils.HibernateUtil;

public class EventTypeHibernate implements EventTypeDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public EventType add(EventType t) {
		// not implemented
		return null;
	}

	@Override
	public EventType getById(Integer id) {
		Session s = hu.getSession();
		EventType e = s.get(EventType.class, id);
		s.close();
		return e;
	}

	@Override
	public Set<EventType> getAll() {
		Session s = hu.getSession();
		String query = "FROM EventType";
		Query<EventType> q = s.createQuery(query, EventType.class);
		List<EventType> typeList = q.getResultList();
		Set<EventType> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(EventType t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(EventType t) {
		// TODO Auto-generated method stub

	}

}
