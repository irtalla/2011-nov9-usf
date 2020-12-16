package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Storytype;
import com.revature.utils.HibernateUtil;

public class StorytypeHibernate implements StorytypeDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Storytype add(Storytype t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Storytype getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Storytype t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Storytype t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Storytype> getAll() {
		Session s = hu.getSession();
		String query = "FROM Storytype";
		Query<Storytype> q = s.createQuery(query, Storytype.class);
		List<Storytype> typeList = q.getResultList();
		Set<Storytype> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}
}
