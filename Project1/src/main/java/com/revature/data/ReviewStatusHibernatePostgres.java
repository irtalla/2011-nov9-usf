package com.revature.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.ReviewStatus;
import com.revature.util.HibernateUtil;

public class ReviewStatusHibernatePostgres implements ReviewStatusDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(ReviewStatus t) throws Exception {
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
	public ReviewStatus getById(Integer id) {
		ReviewStatus rs = new ReviewStatus();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			rs = s.get(ReviewStatus.class, id);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return rs;
	}

	@Override
	public Set<ReviewStatus> getAll() {
		Set<ReviewStatus> status = new HashSet<>(getAllOrdered());
		return status;
	}
	
	@Override
	public List<ReviewStatus> getAllOrdered() {
		List<ReviewStatus> status = new ArrayList<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM ReviewStatus ORDER BY id";
			Query<ReviewStatus> q = s.createQuery(hql, ReviewStatus.class);
			status = q.getResultList();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return status;
	}

	@Override
	public void update(ReviewStatus t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

	@Override
	public void delete(ReviewStatus t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

}
