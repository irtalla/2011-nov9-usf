package com.revature.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.StoryType;
import com.revature.util.HibernateUtil;

public class StoryTypeHibernatePostgres implements StoryTypeDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Override
	public Integer add(StoryType t) throws Exception {
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
			e.getCause().printStackTrace();
		}
		return newId;
	}

	@Override
	public StoryType getById(Integer id) {
		StoryType st = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			st = s.get(StoryType.class, id);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return st;
	}

	@Override
	public Set<StoryType> getAll() {
		Set<StoryType> types = new HashSet<>(getAllOrdered());
		return types;
	}
	
	@Override
	public List<StoryType> getAllOrdered() {
		List<StoryType> types = new ArrayList<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM StoryType ORDER BY id";
			Query<StoryType> q = s.createQuery(hql, StoryType.class);
			types = q.getResultList();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return types;
	}

	@Override
	public void update(StoryType t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}

	}

	@Override
	public void delete(StoryType t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

}
