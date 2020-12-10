package dev.rev.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dev.rev.beans.event;
import dev.rev.beans.reimbForm;
import dev.rev.utils.HibernateUtil;

public class formhuber implements formDAO {


	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public reimbForm add(reimbForm t) throws Exception {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return t;
		
	}

	@Override
	public reimbForm getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<reimbForm> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String query = "FROM reimbForm";
		Query<reimbForm> q = s.createQuery(query, reimbForm.class);
		List<reimbForm> catsList =q.getResultList();
	
		s.close();
		return catsList ;
	}

	@Override
	public void update(reimbForm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(reimbForm t) {
		// TODO Auto-generated method stub
		
	}

}
