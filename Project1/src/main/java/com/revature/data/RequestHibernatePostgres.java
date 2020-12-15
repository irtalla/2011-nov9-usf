package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.Requests;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class RequestHibernatePostgres implements RequestDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(Requests t) throws Exception {
		Integer newId = 0;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			newId = (Integer) s.save(t);
			
			if (newId != 0) {
				s.getTransaction().commit();
			} else {
				s.getTransaction().rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newId;
	}

	@Override
	public Requests getById(Integer id) {
		Requests r = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			r = s.get(Requests.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public Set<Requests> getByRequester(User t) {
		Set<Requests> requests = new HashSet<>();
		
		if (t == null) return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Requests where requester_id = :requester_id";
			Query<Requests> q = s.createQuery(hql, Requests.class);
			q.setParameter("requester_id", t.getId());
			List<Requests> resultList = q.getResultList();
			if (!resultList.isEmpty()) {
				requests = new HashSet<Requests>(resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}

	@Override
	public Set<Requests> getByRequestee(User t) {
		Set<Requests> requests = new HashSet<>();
		
		if (t == null) return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Requests where requestee_id = :requestee_id";
			Query<Requests> q = s.createQuery(hql, Requests.class);
			q.setParameter("requestee_id", t.getId());
			List<Requests> resultList = q.getResultList();
			if (!resultList.isEmpty()) {
				requests = new HashSet<Requests>(resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}
	
	@Override
	public Set<Requests> getAll() {
		Set<Requests> requests = new HashSet<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Requests";
			Query<Requests> q = s.createQuery(hql, Requests.class);
			List<Requests> resultList = q.getResultList();
			if (!resultList.isEmpty()) {
				requests = new HashSet<Requests>(resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}

	@Override
	public void update(Requests t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Requests t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
